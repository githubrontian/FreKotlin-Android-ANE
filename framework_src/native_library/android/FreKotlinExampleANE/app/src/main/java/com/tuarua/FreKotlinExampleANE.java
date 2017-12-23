/*
 *  Copyright 2017 Tua Rua Ltd.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package com.tuarua;

import com.adobe.fre.FREContext;
import com.adobe.fre.FREExtension;
import com.tuarua.frekotlin.FreKotlinContext;

@SuppressWarnings("unused")
public class FreKotlinExampleANE implements FREExtension  {
    private String NAME = "com.tuarua.FreKotlinExampleANE";
    public static final String[] FUNCTIONS = {
            "runStringTests",
            "runNumberTests",
            "runIntTests",
            "runObjectTests",
            "runArrayTests",
            "runBitmapTests",
            "runExtensibleTests",
            "runByteArrayTests",
            "runErrorTests",
            "runErrorTests2",
            "runDataTests",
            "runDateTests"
    };
    public static FreKotlinExampleContext extensionContext;

    @Override
    public void initialize() {
    }

    @Override
    public FREContext createContext(String s) {
        return extensionContext = new FreKotlinExampleContext(NAME, new KotlinController(), FUNCTIONS);
    }

    @Override
    public void dispose() {
        extensionContext.dispose();
    }
}
