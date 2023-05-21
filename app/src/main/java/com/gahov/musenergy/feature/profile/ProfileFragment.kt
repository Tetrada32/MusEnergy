package com.gahov.musenergy.feature.profile

import com.gahov.musenergy.R
import com.gahov.musenergy.arch.ui.fragment.BaseFragment
import com.gahov.musenergy.databinding.FragmentProfileBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileFragment :
    BaseFragment<FragmentProfileBinding, ProfileViewModel>(
        contentLayoutID = R.layout.fragment_profile,
        viewModelClass = ProfileViewModel::class.java
    ) {
}