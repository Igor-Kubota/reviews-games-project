package br.com.imt.dao

import br.com.imt.interfaces.IDaoReview
import br.com.imt.models.Review
import br.com.imt.models.User
import java.sql.DriverManager

class ReviewDAO(val connectionString: String) : IDaoReview{

    override fun insert(obj: Review){
        val connection = DriverManager.getConnection(connectionString)
        val preparedStatement = connection.prepareStatement("INSERT INTO Review (GameId, UserId, Review, Score, 'Date') VALUES (?,?,?,?,?);")
        preparedStatement.setInt(1, obj.gameId)
        preparedStatement.setInt(2, obj.userId)
        preparedStatement.setString(3, obj.review)
        preparedStatement.setInt(4, obj.score)
        preparedStatement.setString(5, obj.date)
        preparedStatement.executeUpdate()
        connection.close()
    }

    override fun update(obj: Review) {
        val connection = DriverManager.getConnection(connectionString)
        val preparedStatement = connection.prepareStatement("""
            UPDATE Review 
            SET GameId=?, UserId=?, Review=?, Score=?, Date=?
            WHERE Id = ?;
            """.trimMargin())
        preparedStatement.setInt(1, obj.gameId)
        preparedStatement.setInt(2, obj.userId)
        preparedStatement.setString(3, obj.review)
        preparedStatement.setInt(4, obj.score)
        preparedStatement.setString(5, obj.date)
        preparedStatement.setInt(6, obj.id)
        preparedStatement.executeUpdate()
        connection.close()
    }

    override fun delete(id: Int, userId: Int) {
        val connection = DriverManager.getConnection(connectionString)
        val preparedStatement = connection.prepareStatement("""
            DELETE FROM Review  
            WHERE id = ? AND UserId = ?;
            """.trimMargin())
        preparedStatement?.setInt(1,id)
        preparedStatement?.setInt(2,userId)
        preparedStatement?.executeUpdate()
        connection.close()
    }



    override fun get(id: Int, userId: Int): Review {
        val connection = DriverManager.getConnection(connectionString)
        val preparedStatement = connection.prepareStatement("""
            SELECT * FROM Review  
            WHERE id = ? AND UserId = ?;
            """.trimMargin())
        preparedStatement?.setInt(1,id)
        preparedStatement?.setInt(2,userId)
        val resultSet = preparedStatement.executeQuery()
        val review = Review(
            resultSet.getInt("Id"),
            resultSet.getInt("GameId"),
            resultSet.getInt("UserId"),
            resultSet.getString("Review"),
            resultSet.getInt("Score"),
            resultSet.getString("Date")
        )
        resultSet.close()
        preparedStatement.close()
        connection.close()
        return review
    }


}