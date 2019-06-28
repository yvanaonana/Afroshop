package com.tsafack.tkswif.afroshop.gestionDesCommandes.gestionDesPaiements.activities;
//
//import android.content.DialogInterface;
//import android.content.Intent;
//import android.net.Uri;
//import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
//import android.view.View;
//
//import com.paypal.android.sdk.payments.PayPalConfiguration;
//import com.paypal.android.sdk.payments.PayPalPayment;
//import com.paypal.android.sdk.payments.PayPalService;
//import com.paypal.android.sdk.payments.PaymentActivity;
import com.tsafack.tkswif.afroshop.R;
//import com.tsafack.tkswif.afroshop.gestionDesCommandes.gestionDesArticles.Extra;
//
//import java.math.BigDecimal;
//
public class PaiementActivity extends AppCompatActivity {
//
//    private static final String TAG = "paymentExample";
//    /**
//     * - Set to PayPalConfiguration.ENVIRONMENT_PRODUCTION to move real money.
//     *
//     * - Set to PayPalConfiguration.ENVIRONMENT_SANDBOX to use your test credentials
//     * from https://developer.paypal.com
//     *
//     * - Set to PayPalConfiguration.ENVIRONMENT_NO_NETWORK to kick the tires
//     * without communicating to PayPal's servers.
//     */
//    private static final String CONFIG_ENVIRONMENT = PayPalConfiguration.ENVIRONMENT_NO_NETWORK;
//
//    // note that these credentials will differ between live & sandbox environments.
//    private static final String CONFIG_CLIENT_ID = "credentials from developer.paypal.com";
//
//    private static final int REQUEST_CODE_PAYMENT = 1;
//    private static final int REQUEST_CODE_FUTURE_PAYMENT = 2;
//    private static final int REQUEST_CODE_PROFILE_SHARING = 3;
//
//
//    private static PayPalConfiguration config = new PayPalConfiguration()
//            .environment(CONFIG_ENVIRONMENT)
//            .clientId(CONFIG_CLIENT_ID)
//            // The following are only used in PayPalFuturePaymentActivity.
//            .merchantName("Example Merchant")
//            .merchantPrivacyPolicyUri(Uri.parse("https://www.example.com/privacy"))
//            .merchantUserAgreementUri(Uri.parse("https://www.example.com/legal"));
//
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paiement);
    }
//
//    public void onBuyPressed(View pressed) {
//        /*
//         * PAYMENT_INTENT_SALE will cause the payment to complete immediately.
//         * Change PAYMENT_INTENT_SALE to
//         *   - PAYMENT_INTENT_AUTHORIZE to only authorize payment and capture funds later.
//         *   - PAYMENT_INTENT_ORDER to create a payment for authorization and capture
//         *     later via calls from your server.
//         *
//         * Also, to include additional payment details and an item list, see getStuffToBuy() below.
//         */
//        AlertDialog dialog = new AlertDialog.Builder(PaiementActivity.this)
//                .setTitle("PayPal")
//                .setMessage("Avec Paypal vous pouvez payer directement avec votre compte Paypal ou avec votre carte de credit.\n" +
//                        "Voulez-vous vraiment payer via ce mode ?")
//                .setIcon(R.drawable.moneybag_48px)
//                .setPositiveButton("OUI", new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int whichButton) {
//                        PayPalPayment thingToBuy = getThingToBuy(PayPalPayment.PAYMENT_INTENT_SALE);
//
//        /*
//         * See getStuffToBuy(..) for examples of some available payment options.
//         */
//
//                        Intent intent = new Intent(PaiementActivity.this, PaymentActivity.class);
//
//                        // send the same configuration for restart resiliency
//                        intent.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION, config);
//
//                        intent.putExtra(PaymentActivity.EXTRA_PAYMENT, thingToBuy);
//
//                        startActivityForResult(intent, REQUEST_CODE_PAYMENT);
//                    }
//                })
//                .setNegativeButton("NON", null).create();
//        dialog.show();
//
//    }
//
//    private PayPalPayment getThingToBuy(String paymentIntent) {
//        Intent intent = getIntent();
//        double prixTotal = intent.getDoubleExtra(Extra.ARTICLE_PANIER, 0);
//        return new PayPalPayment(new BigDecimal(prixTotal), "XAF", "produit",
//                paymentIntent);
//    }
//
}
