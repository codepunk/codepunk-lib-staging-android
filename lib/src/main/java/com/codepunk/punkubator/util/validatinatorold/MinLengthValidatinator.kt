/*
 * Copyright (C) 2018 Codepunk, LLC
 * Author(s): Scott Slater
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.codepunk.punkubator.util.validatinatorold

import android.content.Context
import com.codepunk.punkubator.R

open class MinLengthValidatinator protected constructor(
    context: Context?,
    getInputName: (context: Context?, input: CharSequence?) -> CharSequence?,
    getInvalidMessage: (context: Context?, inputName: CharSequence?) -> CharSequence?,
    getValidMessage: (context: Context?, inputName: CharSequence?) -> CharSequence?,
    protected val minLength: Int
) : Validatinator<CharSequence?>(context, getInputName, getInvalidMessage, getValidMessage) {

    override fun isValid(input: CharSequence?, options: Options): Boolean =
        input != null && input.length >= minLength

    open class Builder(minLength: Int = Integer.MAX_VALUE) :
        AbsBuilder<CharSequence?, MinLengthValidatinator, Builder>() {

        protected val minLength = Math.max(minLength, 0)

        override var getInvalidMessage: (
            context: Context?,
            inputName: CharSequence?
        ) -> CharSequence? = { context, inputName ->
            context?.getString(
                R.string.validatinator_invalid_min,
                inputName,
                this.minLength
            ) ?: throw missingContextException("getInvalidMessage")
        }

        override val thisBuilder: Builder by lazy { this }

        override fun build(): MinLengthValidatinator = MinLengthValidatinator(
            context,
            getInputName,
            getInvalidMessage,
            getValidMessage,
            minLength
        )

    }

}