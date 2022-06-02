package com.example.liquigas.fragment

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.*
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import com.example.liquigas.R
import com.example.liquigas.dao.AppDatabase
import com.example.liquigas.databinding.FragmentHomeBinding
import com.example.liquigas.utils.LocalStorage

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private var database: AppDatabase? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root
        database = AppDatabase.getInstance(root.context)


        var homeButton: Button = root.findViewById(R.id.home_button)

        homeButton.setOnClickListener(object: View.OnClickListener {
            override fun onClick(view: View) {
                Navigation.findNavController(view).navigate(R.id.action_nav_home_to_nav_gas_list)
            }
        })

        val localStorage = LocalStorage(this.requireContext())

        val userId = localStorage.getValue(getString(R.string.user_logged))

        if (userId != null) {
            val databaseUserDAO = database!!.userDAO()

            val user = databaseUserDAO.getUserById(userId)

            val textView: TextView = binding.textHome

            textView.setText("Olá ${user.name}, precisando de um gás?")
        }

        return root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)

        inflater.inflate(R.menu.main, menu)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}