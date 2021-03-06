package tech.ascendio.mvvmstarter.di

import android.content.Context
import tech.ascendio.mvvmstarter.data.api.ApiService
import tech.ascendio.mvvmstarter.data.db.AppDatabase
import tech.ascendio.mvvmstarter.data.repositories.BookRepository
import tech.ascendio.mvvmstarter.utilities.schedulers.IoScheduler
import tech.ascendio.mvvmstarter.utilities.schedulers.MainScheduler
import tech.ascendio.mvvmstarter.utilities.schedulers.NetworkScheduler
import tech.ascendio.mvvmstarter.viewmodels.BookViewModelFactory

/*
 * Copyright (C) 2018 Marian Vasilca@Ascendio TechVision
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

/**
 * Static methods used to inject classes needed for various Activities and Fragments.
 */
object InjectorUtils {
    private fun getBookRepository(context: Context): BookRepository {
        return BookRepository.getInstance(
                ApiService.create(),
                AppDatabase.getInstance(context).bookDao(),
                MainScheduler(),
                IoScheduler(),
                NetworkScheduler()
        )
    }

    fun provideBookViewModelFactory(context: Context): BookViewModelFactory {
        val repository = getBookRepository(context)
        return BookViewModelFactory(repository)
    }
}