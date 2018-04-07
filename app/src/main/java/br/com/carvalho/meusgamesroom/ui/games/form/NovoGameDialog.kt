package br.com.carvalho.meusgamesroom.ui.games.form

import android.app.AlertDialog
import android.app.Dialog
import android.app.DialogFragment
import android.os.AsyncTask
import android.os.Bundle
import android.widget.EditText
import br.com.carvalho.meusgamesroom.DAO.BancoDeDados
import br.com.carvalho.meusgamesroom.R
import br.com.carvalho.meusgamesroom.model.Game

class NovoGameDialog : DialogFragment() {
    private lateinit var builder: AlertDialog.Builder
    private lateinit var etGame: EditText
    private lateinit var etPlataforma: EditText
    override fun onCreateDialog(savedInstanceState: Bundle?):
            Dialog {
        builder = AlertDialog.Builder(activity)
        val v =
                activity.layoutInflater.inflate(R.layout.novo_game_dialog, null)
        etGame = v.findViewById(R.id.etGame)
        etPlataforma = v.findViewById(R.id.etPlataforma)
        builder.setView(v)
        builder.setTitle("Novo Game")
        builder.setPositiveButton("Adicionar") { _, _ ->
            val db =
                    BancoDeDados.getDatabase(activity.applicationContext)
            val game = Game(etGame.text.toString(),
                    etPlataforma.text.toString())
            if (game.nome != "")
                InsertAsyncTask(db!!).execute(game)
        }
        builder.setNegativeButton("Cancelar", null)
        return builder.create()
    }
    private inner class InsertAsyncTask internal
    constructor(appDatabase: BancoDeDados) : AsyncTask<Game, Void,
            String>() {
        private val db: BancoDeDados = appDatabase
        override fun doInBackground(vararg params: Game): String
        {
            db.gameDAO().inserir(params[0])
            return ""
        }
    }
}
