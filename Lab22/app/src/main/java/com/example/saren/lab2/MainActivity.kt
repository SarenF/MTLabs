package com.example.saren.lab2

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.*
import android.app.Person
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.Call
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL
import java.net.URLEncoder
import javax.security.auth.callback.Callback
import android.os.AsyncTask
import android.provider.Settings
import android.widget.Toast
import com.google.gson.Gson
import kotlinx.coroutines.experimental.launch
import java.io.OutputStream
import java.io.OutputStreamWriter
import javax.net.ssl.HttpsURLConnection
import kotlinx.coroutines.experimental.*
import kotlinx.coroutines.*
import kotlinx.coroutines.experimental.android.UI
import org.jetbrains.anko.db.*
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors





class MainActivity : AppCompatActivity() {
    //private var client = OkHttpClient()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var dbh = DBHandler
        database.use {
            createTable("Translations",true,
                "_id" to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
                "Russian" to TEXT,
                "English" to TEXT
            )

            val translations = ArrayList<TranslationsModel>()

            select("Translations", "_id", "Russian", "English")
                .parseList(object: MapRowParser<List<TranslationsModel>>{
                    override fun parseRow(columns: Map<String, Any?>): List<TranslationsModel> {
                        val id = columns.getValue("_id")
                        val rus = columns.getValue("Russian")
                        val eng = columns.getValue("English")

                        val trn = TranslationsModel(id.toString().toLong(), rus.toString(), eng.toString())


                        translations.add(trn)
                        launch(UI) {
                            textView2.text =
                                    "Last translation: "+
                                    "\nID: " + trn.id +
                                    "\nRus: " + trn.Russian+
                                    "\nEng: " + trn.English
                        }

                        return translations
                    }
                })
            translations

        }

    }

    fun translateEvent(view: View) {
        val str: String = editText.text.toString()
        val parseThread = async{
           val response = sendGetRequest(str)
            var gson = Gson()
            var translateEntity = gson?.fromJson(response.toString(), JSONEntity.TranslateInfo::class.java)
            var res = translateEntity.text.get(0)
            return@async res
        }
        launch(UI) {
            val res = parseThread.await()
            textView.text = "Translation:" + res
        }
    }

    private fun sendGetRequest(text:String):String {

        var reqParam = URLEncoder.encode("text", "UTF-8") + "=" + URLEncoder.encode(text, "UTF-8") + "&lang=en-ru"

        val mURL = URL("https://translate.yandex.net/api/v1.5/tr.json/translate?key=trnsl.1.1.20171011T225501Z.99b9b49f7d76c46c.3b42a8de970dce53dbd609ef9ce6d32ac0408b8f&"+reqParam)

        with(mURL.openConnection() as HttpURLConnection) {
            // optional default is GET
            requestMethod = "GET"

            println("URL : $url")
            println("Response Code : $responseCode")

            BufferedReader(InputStreamReader(inputStream)).use {
                val response = StringBuffer()

                var inputLine = it.readLine()
                while (inputLine != null) {
                    response.append(inputLine)
                    inputLine = it.readLine()
                }
                it.close()
                println("Response : $response")
                var gson = Gson()
                var translateEntity = gson?.fromJson(response.toString(), JSONEntity.TranslateInfo::class.java)
                println(translateEntity.text.get(0))//translation

                database.use{
                    insert("Translations",
                        "English" to text,
                        "Russian" to translateEntity.text.get(0))
                }




                database.use {

                    val translations = ArrayList<TranslationsModel>()

                    select("Translations", "_id", "Russian", "English")
                        .parseList(object: MapRowParser<List<TranslationsModel>>{
                            override fun parseRow(columns: Map<String, Any?>): List<TranslationsModel> {
                                val id = columns.getValue("_id")
                                val rus = columns.getValue("Russian")
                                val eng = columns.getValue("English")

                                val trn = TranslationsModel(id.toString().toLong(), rus.toString(), eng.toString())


                                translations.add(trn)
                                launch(UI) {
                                    textView2.text =
                                            "Last translation: "+
                                            "\nID: " + trn.id +
                                            "\nRus: " + trn.Russian+
                                            "\nEng: " + trn.English
                                }

                                return translations
                            }
                        })
                    translations
                }

                /*
println("DB select: " +
        "English: " + result.get(1)+"" +
        "Russian: " + result.get(2))
        */


                return response.toString()
            }
        }
    }

}


