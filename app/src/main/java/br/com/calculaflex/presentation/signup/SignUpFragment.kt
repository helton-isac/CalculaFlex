package br.com.calculaflex.presentation.signup

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.navigation.fragment.NavHostFragment
import br.com.calculaflex.R
import br.com.calculaflex.presentation.base.BaseFragment

class SignUpFragment : BaseFragment() {
    override val layout = R.layout.fragment_sign_up

    private lateinit var tvTerms: TextView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tvTerms = view.findViewById(R.id.tvTerms)

        tvTerms.setOnClickListener {
            NavHostFragment.findNavController(this)
                .navigate(R.id.action_signUpFragment_to_termsFragment)
        }
    }
}