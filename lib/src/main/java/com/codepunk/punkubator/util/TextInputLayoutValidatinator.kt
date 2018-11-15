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

package com.codepunk.punkubator.util

import com.google.android.material.textfield.TextInputLayout
import com.codepunk.punkubator.util.Validatinator.ValidatinatorListener

class TextInputLayoutValidatinator(
    private val layout: TextInputLayout,
    wrapped: Validatinator<CharSequence?>
) : ValidatinatorWrapper<Void?, CharSequence?>(wrapped) {

    override fun getWrappedInput(input: Void?): CharSequence? = layout.editText?.text

    override fun onValid(validatinator: Validatinator<Void?>, input: Void?) {
        layout.error = null
    }

    override fun onInvalid(
        validatinator: Validatinator<Void?>,
        input: Void?,
        message: CharSequence?
    ) {
        layout.error = message
        layout.requestFocus()
    }

    fun validate(listener: ValidatinatorListener<Void?>? = null): Boolean {
        return super.validate(null, listener)
    }

}
