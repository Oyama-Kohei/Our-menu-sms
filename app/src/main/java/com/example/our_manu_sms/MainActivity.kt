package com.example.our_manu_sms

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextMenu
import android.view.Menu
import android.view.MenuItem
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        registerForContextMenu(menuImage)
    }


    //メニューを表示する
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    //メニューが押下された場合の挙動
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item?.itemId){
            R.id.home ->{
                menuImage.setImageResource(R.drawable.z01)
                menuText.setText(R.string.home_text)
                return true
            }
            R.id.one ->{
                menuImage.setImageResource(R.drawable.c01)
                menuText.setText(R.string.one_text)
                return true
            }
            R.id.two ->{
                menuImage.setImageResource(R.drawable.c02)
                menuText.setText(R.string.two_text)
                return true
            }
            R.id.three ->{
                menuImage.setImageResource(R.drawable.c03)
                menuText.setText(R.string.three_text)
                return true
            }
            R.id.joker ->{
                menuImage.setImageResource(R.drawable.x01)
                menuText.setText(R.string.joker_text)
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreateContextMenu(
        //コンテキストメニュー
        menu: ContextMenu?,

        //長押しされたビュー
        v: View?,

        //コンテキストメニュー作成に関する追加情報
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        if(menuText.text.isNotEmpty()){
            menuInflater.inflate(R.menu.context, menu)
        }
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {

        when(item?.itemId){
            R.id.mail -> {

                //putExtraメソッドで件名として渡す
                val subject = getString(R.string.app_name)

                //メール本文
                val text = "Receive text ”${menuText.text}” from system"

                //UriオブジェクトのfromPartsメソッド
                val uri = Uri.fromParts("Our_menu_sms_system", "kohei.perc.sd@gmail.com", null)

                val intent = Intent(Intent.ACTION_SENDTO, uri)
                intent.putExtra(Intent.EXTRA_SUBJECT, subject)
                intent.putExtra(Intent.EXTRA_TEXT, text)

                //resolveActivityメソッドはインテントを処理できるアプリが存在するかどうかを判定
                //存在するメソッドがない場合はnullを返す
                if(intent.resolveActivity(packageManager) != null){
                    startActivity(intent)
                }
                else{
                    menuText.setText(R.string.error_text)
                }
                return true
            }
            R.id.sms ->{
                val text = "Receive text ”${menuText.text}” from system"
                val uri = Uri.fromParts("Our_menu_sms_system", "08035463327", null)

                val intent = Intent(Intent.ACTION_SENDTO, uri)
                if(intent.resolveActivity(packageManager) != null){
                    startActivity(intent)
                }

                return true
            }
        }
        return super.onContextItemSelected(item)
    }
}