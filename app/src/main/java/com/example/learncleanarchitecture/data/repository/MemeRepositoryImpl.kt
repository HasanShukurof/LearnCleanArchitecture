package com.example.learncleanarchitecture.data.repository

import android.widget.Toast
import com.example.learncleanarchitecture.data.remote.api.MemeApi
import com.example.learncleanarchitecture.data.remote.dto.MemeDto
import com.example.learncleanarchitecture.data.remote.mapper.toDomain
import com.example.learncleanarchitecture.domain.model.Meme
import com.example.learncleanarchitecture.domain.repository.MemeRepository
import com.example.learncleanarchitecture.domain.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class MemeRepositoryImpl @Inject constructor(private val api: MemeApi): MemeRepository {
    override suspend fun getMemes(): Flow<Resource<List<Meme>>> = flow {
        emit(Resource.Loading())

        // cache mentiqini oyren basha dushve yaz, sonra try and cach yazilir

        try {
            val response = api.getMemes()
            if (response.isSuccessful && response.body() != null){
                val image = response.body()!!.data.memeList.toDomain()
                emit(Resource.Success(image))
            } else {
                emit(Resource.Error("API xetasi: ${response.message()}"))
            }
        } catch (e: HttpException){
            emit(Resource.Error("HTTP xetasi: ${e.code()} -> ${e.message()}"))
        } catch (e: IOException) {
            emit(Resource.Error("Internet baglantinizi yoxlayin"))
        } catch (e: Exception) {
            emit(Resource.Error("Gozlenilmez xeta bash verdi: ${e.localizedMessage}"))
        }
    }
}