package br.com.imt.service

import br.com.imt.dto.*
import br.com.imt.interfaces.IDaoGames
import br.com.imt.interfaces.IServiceGames
import br.com.imt.models.Games


class GamesService(val dao: IDaoGames): IServiceGames {

    override fun insert(obj: CreateGamesDTO){
        val game = Games(0, obj.name, obj.summary, obj.developer, obj.genre, obj.score, obj.img, obj.release, obj.consoles)
        dao.insert(game)
    }

    override fun update(obj: GamesDTO){
        val game = Games(obj.id, obj.name, obj.summary, obj.developer, obj.genre, obj.score, obj.img, obj.release, obj.consoles)
        dao.update(game)
    }

    override fun getAll(): List<GamesDTO>{
        val games = dao.getAll()
        return games.map{g -> GamesDTO(g.id, g.name, g.summary,g.developer,g.genre,g.score,g.img,g.release,g.consoles)}
    }

    override fun get(id: String): GameWithReviewsDTO{
        val g = dao.get(id.toInt())
        val dto = GameWithReviewsDTO(g.id, g.name, g.summary,g.developer,g.genre,g.score,g.img,g.release,g.consoles)
        dto.reviews = g.reviews?.map{ r -> ReviewGamesDTO(r.id, r.review, r.score, r.date, UserDTO(r.user!!.id,r.user!!.name, r.user!!.email) ) }
        return dto;
    }

    override fun delete(id: String){
        dao.delete(id.toInt())
    }
}