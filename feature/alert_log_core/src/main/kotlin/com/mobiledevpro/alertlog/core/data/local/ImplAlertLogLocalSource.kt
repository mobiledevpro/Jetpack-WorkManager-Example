/*
 * Copyright 2021 | Dmitri Chernysh | http://mobile-dev.pro
 *
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
package com.mobiledevpro.alertlog.core.data.local

import com.mobiledevpro.database.AppDatabase
import com.mobiledevpro.database.entity.StockAlertEntity
import io.reactivex.Completable
import io.reactivex.Observable


class ImplAlertLogLocalSource(
    private val database: AppDatabase
) : AlertLogLocalSource {

    override fun get(): Observable<List<StockAlertEntity>> =
        database.stockAlertDao
            .selectAll()
            .onErrorReturn {
                emptyList<StockAlertEntity>()
            }

    override fun insert(alert: StockAlertEntity): Completable =
        database.stockAlertDao
            .insert(alert)
}