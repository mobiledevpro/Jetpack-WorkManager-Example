/*
 * Copyright 2020 | Dmitri Chernysh | http://mobile-dev.pro
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
package com.mobiledevpro.home.di

import androidx.fragment.app.Fragment
import com.mobiledevpro.home.view.HomeFragment
import com.mobiledevpro.home.view.HomeViewModel
import com.mobiledevpro.worker.WorkManagerUtil
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.scope.createScope
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module


private val featureHomeModule = module {
    scope<HomeFragment> {
        viewModel {
            HomeViewModel(
                workMangerUtil = get()
            )
        }

        scoped {
            WorkManagerUtil(
                appContext = androidApplication().applicationContext
            )
        }

    }
}

fun Fragment.inject() : Lazy<HomeViewModel> = lazy(LazyThreadSafetyMode.NONE) {
    loadKoinModules(featureHomeModule)

    val fragmentScope = this.createScope(this)

    fragmentScope.get()
}
