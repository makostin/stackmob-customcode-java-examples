/**
 * Copyright 2012-2013 StackMob
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.stackmob.example;

import com.stackmob.core.customcode.CustomCodeMethod;
import com.stackmob.core.rest.ProcessedAPIRequest;
import com.stackmob.core.rest.ResponseToProcess;
import com.stackmob.sdkapi.SDKServiceProvider;

import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HelloWorld implements CustomCodeMethod {

   @Override
    public String getMethodName() {
        return "register";
    }

    @Override
    public List<String> getParams() {
        return Arrays.asList("device_id","user_name");
    }

    @Override
    public ResponseToProcess execute(ProcessedAPIRequest request, SDKServiceProvider sdkServiceProvider) {

        String deviceId = request.getParams().get("device_id");
        String userId = request.getParams().get("user_name");
//        String facebookId = request.getParams().get("facebook_id");

        DataService ds = sdkServiceProvider.getDataService();

        HashMap<String, SMValue> device = new HashMap<String, SMValue>();

        device.put("device_id", new SMString(deviceId)); //string
        device.put("device_name", new SMString(deviceId + "_name")); //string
        device.put("sm_owner", new SMString(userId)); //string


        try {

            ds.createObject("device", new SMObject(device));

        } catch (InvalidSchemaException ise) {
            ise.printStackTrace();
        } catch (DatastoreException dse) {
            dse.printStackTrace();
        }
        return new ResponseToProcess(HttpURLConnection.HTTP_OK, device);
    }

}
