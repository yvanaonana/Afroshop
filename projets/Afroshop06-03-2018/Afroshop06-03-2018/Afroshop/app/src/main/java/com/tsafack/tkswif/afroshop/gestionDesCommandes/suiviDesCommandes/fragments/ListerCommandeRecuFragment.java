package com.tsafack.tkswif.afroshop.gestionDesCommandes.suiviDesCommandes.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.tsafack.tkswif.afroshop.R;
import com.tsafack.tkswif.afroshop.entities.entitiesCustom.CommamdeCustom;
import com.tsafack.tkswif.afroshop.gestionDesCommandes.Configuration.traitement.GestionCommande;
import com.tsafack.tkswif.afroshop.gestionDesCommandes.suiviDesCommandes.adapter.ListCommandeAdapter;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ListerCommandeRecuFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ListerCommandeRecuFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ListerCommandeRecuFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;
    ListView listView;

    private ArrayList<CommamdeCustom> commamdeCustoms = new ArrayList<>();
    public ListerCommandeRecuFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ListerCommandeRecuFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ListerCommandeRecuFragment newInstance(String param1, String param2) {
        ListerCommandeRecuFragment fragment = new ListerCommandeRecuFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_lister_commande_recu, container, false);

        listView = (ListView) view.findViewById(R.id.listercommanderecu_listview);

        initView();

        ListCommandeAdapter adapter = new ListCommandeAdapter(getContext(), commamdeCustoms);
        listView.setAdapter(adapter);

        return view;
    }

    private void initView() {

        GestionCommande gestionCommande = new GestionCommande(getContext());
        commamdeCustoms = gestionCommande.listerCommandeRecu(listView);

    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }


    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
