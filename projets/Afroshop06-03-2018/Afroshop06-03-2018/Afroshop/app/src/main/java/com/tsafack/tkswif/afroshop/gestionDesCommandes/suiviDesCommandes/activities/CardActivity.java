package com.tsafack.tkswif.afroshop.gestionDesCommandes.suiviDesCommandes.activities;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
//import com.paypal.android.sdk.payments.PayPalAuthorization;
//import com.paypal.android.sdk.payments.PayPalConfiguration;
//import com.paypal.android.sdk.payments.PayPalFuturePaymentActivity;
//import com.paypal.android.sdk.payments.PayPalPayment;
//import com.paypal.android.sdk.payments.PayPalProfileSharingActivity;
//import com.paypal.android.sdk.payments.PayPalService;
//import com.paypal.android.sdk.payments.PaymentActivity;
//import com.paypal.android.sdk.payments.PaymentConfirmation;
import com.tsafack.tkswif.afroshop.R;
import com.tsafack.tkswif.afroshop.entities.entitiesBD.AgenceBancaire;
import com.tsafack.tkswif.afroshop.entities.entitiesBD.ArticleCommande;
import com.tsafack.tkswif.afroshop.entities.entitiesCustom.ArticlePanier;
import com.tsafack.tkswif.afroshop.entities.entitiesCustom.CommamdeCustom;
import com.tsafack.tkswif.afroshop.entities.entitiesCustom.CommandePanier;
import com.tsafack.tkswif.afroshop.gestionDesCommandes.Configuration.SharePreferenceCart;
import com.tsafack.tkswif.afroshop.gestionDesCommandes.Configuration.traitement.GestionCommande;
import com.tsafack.tkswif.afroshop.gestionDesCommandes.gestionDesArticles.activities.Listes_des_produits_categorie;
import com.tsafack.tkswif.afroshop.gestionDesCommandes.suiviDesCommandes.adapter.AddToCardAdapter;
import com.tsafack.tkswif.afroshop.gestionDesUtilisateurs.configuration.SessionManager;

import org.json.JSONException;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


public class CardActivity extends AppCompatActivity {

    //paramatrees paypal*********************************************************************//

    private static final String TAG = "paymentExample";
    /**
     * - Set to PayPalConfiguration.ENVIRONMENT_PRODUCTION to move real money.
     * <p>
     * - Set to PayPalConfiguration.ENVIRONMENT_SANDBOX to use your test credentials
     * from https://developer.paypal.com
     * <p>
     * - Set to PayPalConfiguration.ENVIRONMENT_NO_NETWORK to kick the tires
     * without communicating to PayPal's servers.
     */
//    private static final String CONFIG_ENVIRONMENT = PayPalConfiguration.ENVIRONMENT_NO_NETWORK;
//
//    // note that these credentials will differ between live & sandbox environments.
//    private static final String CONFIG_CLIENT_ID = "credentials from developer.paypal.com";
//
//    private static final int REQUEST_CODE_PAYMENT = 1;
//    private static final int REQUEST_CODE_FUTURE_PAYMENT = 2;
//    private static final int REQUEST_CODE_PROFILE_SHARING = 3;
//
//    private static PayPalConfiguration config = new PayPalConfiguration()
//            .environment(CONFIG_ENVIRONMENT)
//            .clientId(CONFIG_CLIENT_ID)
//            // The following are only used in PayPalFuturePaymentActivity.
//            .merchantName("Example Merchant")
//            .merchantPrivacyPolicyUri(Uri.parse("https://www.example.com/privacy"))
//            .merchantUserAgreementUri(Uri.parse("https://www.example.com/legal"));
//
//    //*****************************************************************************************//
//
    AddToCardAdapter adapter;
    List<ArticleCommande> articleCommandes = new ArrayList<>();
    ListView listView;
    TextView outputPrixTotal;
    FloatingActionButton commander;
    Resources res;
//
    SharePreferenceCart preferenceCart;
    List<ArticlePanier> articlesInCart;
    double prixTotal;

    private int nombreArticlePanier = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card);

        commander = (FloatingActionButton) findViewById(R.id.addproducttocard_commander);

        listView = (ListView) findViewById(R.id.addproducttocard_listView);

        outputPrixTotal = (TextView) findViewById(R.id.addproducttocard_prixtotal);

        res = getResources();
//
//        Intent intent = new Intent(this, PayPalService.class);
//        intent.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION, config);
//        startService(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();

        preferenceCart = new SharePreferenceCart(CardActivity.this);
        articlesInCart = preferenceCart.retrieveArticlesFromCart();

        prixTotal = 0;
        adapter = new AddToCardAdapter(this, (ArrayList<ArticlePanier>) articlesInCart);
        listView.setAdapter(adapter);
        listView.setItemChecked(1, true);
        for (ArticlePanier articlePanier : articlesInCart) {
            prixTotal = prixTotal + articlePanier.getPrixUnitaireArticlePanier() * articlePanier.getQuantiteArticlePanier();
        }

        outputPrixTotal.setText("Prix Total : " + prixTotal + " XAF");

        final double finalPrixTotal = prixTotal;
        commander.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent(CardActivity.this, PaiementActivity.class);
//                intent.putExtra(Extra.ARTICLE_PANIER, finalPrixTotal);
//                startActivity(intent);

                LayoutInflater factory = LayoutInflater.from(CardActivity.this);
                final View view1 = factory.inflate(R.layout.dialog_choix_paiement, null);
                RadioGroup radioGroup = (RadioGroup) view1.findViewById(R.id.choixpaiement_checkbox_group);


                @SuppressLint("ResourceType") final AlertDialog alertDialog = new AlertDialog.Builder(CardActivity.this)
                        .setTitle(res.getString(R.string.titre_mode_paiement_dialog))
                        .setView(view1)
                        .setIcon(R.drawable.ic_payment_settings)
                        .setNegativeButton(res.getString(R.string.annuler), null)
                        .create();
                alertDialog.show();

                radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup radioGroup, int i) {
//                        if (radioGroup.getCheckedRadioButtonId() == R.id.choixpaiement_checkbox_paiementelectronique) {
//                            PayPalPayment thingToBuy = getThingToBuy(PayPalPayment.PAYMENT_INTENT_SALE);
//
//        /*
//         * See getStuffToBuy(..) for examples of some available payment options.
//         */
//
//                            Intent intent = new Intent(CardActivity.this, PaymentActivity.class);
//
//                            // send the same configuration for restart resiliency
//                            intent.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION, config);
//
//                            intent.putExtra(PaymentActivity.EXTRA_PAYMENT, thingToBuy);
//
//                            startActivityForResult(intent, REQUEST_CODE_PAYMENT);
//                        }
                        if (radioGroup.getCheckedRadioButtonId() == R.id.choixpaiement_checkbox_paiementespece) {
                            LayoutInflater factory = LayoutInflater.from(CardActivity.this);
                            final View view = factory.inflate(R.layout.list_agence_bancaire, null);
                            ListView listAgence = (ListView) view.findViewById(R.id.listagencebancaire_listView);
                            GestionCommande gestionCommande = new GestionCommande(CardActivity.this);
                            final ArrayList<AgenceBancaire> agenceBancaires = gestionCommande.listerAgencesBancaire(listAgence, view);
                            listAgence.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                    passerCommande(agenceBancaires.get(i).getLoginAgenceBancaire(), false);
                                    alertDialog.dismiss();
                                }
                            });
                        }
                    }
                });

            }
        });
    }
//
//
//    private PayPalPayment getThingToBuy(String paymentIntent) {
//        return new PayPalPayment(new BigDecimal("0.01"), "XAF", "sample item",
//                paymentIntent);
//    }
//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        if (requestCode == REQUEST_CODE_PAYMENT) {
//            if (resultCode == Activity.RESULT_OK) {
//                PaymentConfirmation confirm =
//                        data.getParcelableExtra(PaymentActivity.EXTRA_RESULT_CONFIRMATION);
//                if (confirm != null) {
//                    try {
//                        Log.i(TAG, confirm.toJSONObject().toString(4));
//                        Log.i(TAG, confirm.getPayment().toJSONObject().toString(4));
//                        /**
//                         *  TODO: send 'confirm' (and possibly confirm.getPayment() to your server for verification
//                         * or consent completion.
//                         * See https://developer.paypal.com/webapps/developer/docs/integration/mobile/verify-mobile-payment/
//                         * for more details.
//                         *
//                         * For sample mobile backend interactions, see
//                         * https://github.com/paypal/rest-api-sdk-python/tree/master/samples/mobile_backend
//                         */
//                        displayResultText("PaymentConfirmation info received from PayPal");
//
//
//                    } catch (JSONException e) {
//                        Log.e(TAG, "an extremely unlikely failure occurred: ", e);
//                    }
//                }
//            } else if (resultCode == Activity.RESULT_CANCELED) {
//                Log.i(TAG, "The user canceled.");
//            } else if (resultCode == PaymentActivity.RESULT_EXTRAS_INVALID) {
//                Log.i(
//                        TAG,
//                        "An invalid Payment or PayPalConfiguration was submitted. Please see the docs.");
//            }
//        } else if (requestCode == REQUEST_CODE_FUTURE_PAYMENT) {
//            if (resultCode == Activity.RESULT_OK) {
//                PayPalAuthorization auth =
//                        data.getParcelableExtra(PayPalFuturePaymentActivity.EXTRA_RESULT_AUTHORIZATION);
//                if (auth != null) {
//                    try {
//                        Log.i("FuturePaymentExample", auth.toJSONObject().toString(4));
//
//                        String authorization_code = auth.getAuthorizationCode();
//                        Log.i("FuturePaymentExample", authorization_code);
//
//                        sendAuthorizationToServer(auth);
//                        displayResultText("Future Payment code received from PayPal");
//
//                    } catch (JSONException e) {
//                        Log.e("FuturePaymentExample", "an extremely unlikely failure occurred: ", e);
//                    }
//                }
//            } else if (resultCode == Activity.RESULT_CANCELED) {
//                Log.i("FuturePaymentExample", "The user canceled.");
//            } else if (resultCode == PayPalFuturePaymentActivity.RESULT_EXTRAS_INVALID) {
//                Log.i(
//                        "FuturePaymentExample",
//                        "Probably the attempt to previously start the PayPalService had an invalid PayPalConfiguration. Please see the docs.");
//            }
//        } else if (requestCode == REQUEST_CODE_PROFILE_SHARING) {
//            if (resultCode == Activity.RESULT_OK) {
//                PayPalAuthorization auth =
//                        data.getParcelableExtra(PayPalProfileSharingActivity.EXTRA_RESULT_AUTHORIZATION);
//                if (auth != null) {
//                    try {
//                        Log.i("ProfileSharingExample", auth.toJSONObject().toString(4));
//
//                        String authorization_code = auth.getAuthorizationCode();
//                        Log.i("ProfileSharingExample", authorization_code);
//
//                        sendAuthorizationToServer(auth);
//                        displayResultText("Profile Sharing code received from PayPal");
//
//                    } catch (JSONException e) {
//                        Log.e("ProfileSharingExample", "an extremely unlikely failure occurred: ", e);
//                    }
//                }
//            } else if (resultCode == Activity.RESULT_CANCELED) {
//                Log.i("ProfileSharingExample", "The user canceled.");
//            } else if (resultCode == PayPalFuturePaymentActivity.RESULT_EXTRAS_INVALID) {
//                Log.i(
//                        "ProfileSharingExample",
//                        "Probably the attempt to previously start the PayPalService had an invalid PayPalConfiguration. Please see the docs.");
//            }
//        }
//    }
//
//    private void sendAuthorizationToServer(PayPalAuthorization authorization) {
//
//        /**
//         * TODO: Send the authorization response to your server, where it can
//         * exchange the authorization code for OAuth access and refresh tokens.
//         *
//         * Your server must then store these tokens, so that your server code
//         * can execute payments for this user in the future.
//         *
//         * A more complete example that includes the required app-server to
//         * PayPal-server integration is available from
//         * https://github.com/paypal/rest-api-sdk-python/tree/master/samples/mobile_backend
//         */
//
//    }
//
//    public void onFuturePaymentPurchasePressed(View pressed) {
//        // Get the Client Metadata ID from the SDK
//        String metadataId = PayPalConfiguration.getClientMetadataId(this);
//
//        Log.i("FuturePaymentExample", "Client Metadata ID: " + metadataId);
//
//        // TODO: Send metadataId and transaction details to your server for processing with
//        // PayPal...
//        displayResultText("Client Metadata Id received from SDK");
//    }
//
//    protected void displayResultText(String result) {
//        passerCommande("PayPal", true);
//        Toast.makeText(
//                getApplicationContext(),
//                result, Toast.LENGTH_LONG)
//                .show();
//    }
//
    public void passerCommande(String loginAgenceBancaire, boolean payer) {

        List<ArticleCommande> articleCommandeList = new ArrayList<>();
        for (ArticlePanier articlePanier : articlesInCart) {
            ArticleCommande articleCommande = new ArticleCommande();
            articleCommande.setIdAgent(articlePanier.getIdAgent());
            articleCommande.setIdFournisseurArticle(articlePanier.getIdFournisseurArticle());
            articleCommande.setQteArticleCommande(articlePanier.getQuantiteArticlePanier());
            articleCommande.setRefArticle(articlePanier.getRefArticle());
            SessionManager sessionManager = new SessionManager(CardActivity.this);
            articleCommande.setIdClient(sessionManager.getUser().getIdUtilisateur());
            articleCommandeList.add(articleCommande);
        }

        CommandePanier commandePanier = new CommandePanier(loginAgenceBancaire, payer, articleCommandeList);
        Gson gson;
        GsonBuilder builder = new GsonBuilder();
        gson = builder.create();
        String articleCommandes = gson.toJson(commandePanier);
        GestionCommande gestionCommande = new GestionCommande(CardActivity.this);
        gestionCommande.enregisterCommande(articleCommandes);
        Intent intent = new Intent(CardActivity.this, ListeCommandeActivity.class);
        startActivity(intent);
    }
//
//    @Override
//    public void onDestroy() {
//        // Stop service when done
//        stopService(new Intent(this, PayPalService.class));
//        super.onDestroy();
//    }
//
}
