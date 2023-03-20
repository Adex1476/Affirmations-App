/*
 * Copyright (C) 2021 The Android Open Source Project
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
package com.example.affirmations.data
import com.example.affirmations.R
import com.example.affirmations.model.Affirmation
/**
 * [Datasource] generates a list of [Affirmation]
 */
class Datasource() {
    fun loadAffirmations(): List<Affirmation> {
        return listOf<Affirmation>(
            Affirmation(11, R.string.affirmation1,  R.string.description1, R.drawable.image1, "987654321", "blankmail@gmail.com", "Affirmations for you!"),
            Affirmation(12, R.string.affirmation2, R.string.description2, R.drawable.image2, "987654321", "blankmail@gmail.com", "Affirmations for you!"),
            Affirmation(13, R.string.affirmation3, R.string.description3, R.drawable.image3, "987654321", "blankmail@gmail.com", "Affirmations for you!"),
            Affirmation(14, R.string.affirmation4, R.string.description4, R.drawable.image4, "987654321", "blankmail@gmail.com", "Affirmations for you!"),
            Affirmation(15, R.string.affirmation5, R.string.description5, R.drawable.image5, "987654321", "blankmail@gmail.com", "Affirmations for you!"),
            Affirmation(16, R.string.affirmation6, R.string.description6, R.drawable.image6, "987654321", "blankmail@gmail.com", "Affirmations for you!"),
            Affirmation(17, R.string.affirmation7, R.string.description7, R.drawable.image7, "987654321", "blankmail@gmail.com", "Affirmations for you!"),
            Affirmation(18, R.string.affirmation8, R.string.description8, R.drawable.image8, "987654321", "blankmail@gmail.com", "Affirmations for you!"),
            Affirmation(19, R.string.affirmation9, R.string.description9, R.drawable.image9, "987654321", "blankmail@gmail.com", "Affirmations for you!"),
            Affirmation(20, R.string.affirmation10, R.string.description10, R.drawable.image10, "987654321", "blankmail@gmail.com", "Affirmations for you!"))
    }
}
