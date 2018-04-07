package br.com.carvalho.meusgamesroom.ui.games.Lista

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_lista_games.*

import android.arch.lifecycle.ViewModelProviders
import android.support.v7.widget.LinearLayoutManager
import br.com.carvalho.meusgamesroom.ui.games.form.NovoGameDialog
import br.com.carvalho.meusgamesroom.R
import br.com.carvalho.meusgamesroom.model.Game
import kotlinx.android.synthetic.main.content_lista_games.*

class ListaGamesActivity : AppCompatActivity() {

    private var adapter: GameAdapter? = null
    private var games: List<Game> = listOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_games)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            val dialog = NovoGameDialog()
            dialog.show(fragmentManager, "CriarJogo")
        }


        mostrarDados();
        rvGames.layoutManager = LinearLayoutManager(this)
        adapter = GameAdapter(games!!)
        rvGames.adapter = adapter

    }
    private fun mostrarDados() {
        //of() — indica a activity ou Fragment em que o ViewModel    será utilizado
        //get() — indica o ViewModel que será utilizado.
         ViewModelProviders.of(this).get(ListaGameViewModel::class.java)
                        .games
                        .observe(this, Observer<List<Game>> { games ->
                            adapter?.setList(games!!)
                            rvGames.adapter.notifyDataSetChanged()
                        })
    }
/*
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_lista_games, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    } */
}
