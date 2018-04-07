package br.com.carvalho.meusgamesroom.DAO

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import android.arch.persistence.room.Update
import br.com.carvalho.meusgamesroom.model.Game

@Dao
interface GameDAO {
    @Insert
    fun inserir(game: Game)
    @Query("SELECT * FROM Game")
    fun lerGames(): LiveData<List<Game>>
    @Query("SELECT * FROM Game WHERE id = :arg0")
    fun buscarPor(id: Int): Game
    @Update
    fun atualizar(game: Game)
    @Delete
    fun apagar(game: Game)
}
