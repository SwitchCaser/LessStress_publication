
package com.spicysauce.lessstress;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import com.anjlab.android.iab.v3.BillingProcessor;
import com.anjlab.android.iab.v3.PurchaseInfo;
import com.spicysauce.lessstress.R;
import java.util.ArrayList;
import java.util.List;
public class Store extends AppCompatActivity implements BillingProcessor.IBillingHandler {
    private BillingProcessor billingProcessor;
    private ArrayList<ArrayList<ImageButton>> categories;
    private ImageButton tempButton;
    private PurchaseInfo purchaseTransactionDetails = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store);
        tempButton = findViewById(R.id.carpet1);
        ImageButton back_store = findViewById(R.id.back_store);
        back_store.setOnClickListener(v -> {
            Intent back2 = new Intent(Store.this, Tamagochi.class);
            startActivity(back2);
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        });
        billingProcessor = new BillingProcessor(this, getResources().getString(R.string.console_license), this);
        billingProcessor.initialize();
    }
    @Override
    public void onProductPurchased(@NonNull String productId, @Nullable PurchaseInfo details)
    {
        Log.d("onProductPurchased", "Thank You and etc.");
    }
    @Override
    public void onPurchaseHistoryRestored() {
    }
    @Override
    public void onBillingError(int errorCode, @Nullable Throwable error) {
    }
    @Override
    public void onBillingInitialized() {
        purchaseTransactionDetails = billingProcessor.getPurchaseInfo("window2");
        billingProcessor.loadOwnedPurchasesFromGoogleAsync(new BillingProcessor.IPurchasesResponseListener() {
            @Override
            public void onPurchasesSuccess() {
                Log.d("loadOwnedPurchasesFromGoogleAsync", "Success");
            }
            @Override
            public void onPurchasesError() {
                Log.d("loadOwnedPurchasesFromGoogleAsync", "Error");
            }
        });
        tempButton.setOnClickListener(v ->
        {
            if (!billingProcessor.isPurchased("window2")) {
                billingProcessor.purchase(this, "window2");
                Log.d("isPurchased", "purchased");
            }
        });
    }
    @Override
    public void onDestroy() {
        if (billingProcessor != null)
            billingProcessor.release();
        super.onDestroy();
    }
}