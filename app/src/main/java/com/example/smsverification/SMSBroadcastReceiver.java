package com.example.smsverification;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.gms.auth.api.phone.SmsRetriever;
import com.google.android.gms.common.api.CommonStatusCodes;
import com.google.android.gms.common.api.Status;

import java.util.Objects;

public class SMSBroadcastReceiver extends BroadcastReceiver {

    public SMSBroadcastReceiverListner smsBroadcastReceiverListener;

    @Override
    public void onReceive(Context context, Intent intent) {
        if(Objects.equals(intent.getAction(), SmsRetriever.SMS_RETRIEVED_ACTION)){

            Bundle extras = intent.getExtras();
            Status smsRetriverStatus = (Status) extras.get(SmsRetriever.EXTRA_STATUS);

            switch(smsRetriverStatus.getStatusCode()){
                case CommonStatusCodes
                        .SUCCESS:
                    Intent messageIntent = extras.getParcelable(SmsRetriever.EXTRA_CONSENT_INTENT);
                    smsBroadcastReceiverListener.onSuccess(messageIntent);
                    break;
                case CommonStatusCodes.TIMEOUT:
                    smsBroadcastReceiverListener.onFailure();
                    break;


            }


        }
    }

    public interface SMSBroadcastReceiverListner{
        void onSuccess(Intent intent);
        void onFailure();
    }
}
