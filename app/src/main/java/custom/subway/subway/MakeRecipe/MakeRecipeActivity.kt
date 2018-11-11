package custom.subway.subway.MakeRecipe

import android.databinding.DataBindingUtil
import android.os.Bundle
import custom.subway.subway.Menu.MenuViewModel
import custom.subway.subway.R
import custom.subway.subway.Utility.BaseActivity
import custom.subway.subway.databinding.ActivityMakeRecipeBinding

class MakeRecipeActivity : BaseActivity() {

    lateinit var makeRecipeViewModel: MakeRecipeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initDataBinding()
    }


    fun initDataBinding() {
        makeRecipeViewModel = MakeRecipeViewModel(this@MakeRecipeActivity)
        val binding: ActivityMakeRecipeBinding = DataBindingUtil.setContentView(this, R.layout.activity_make_recipe)

        with(binding) {
            makeViewModel = makeRecipeViewModel
            bottomMenu!!.menuViewModel = MenuViewModel.getInstance(application)
            fragmentManager = supportFragmentManager
            makeRecipeActivity = this@MakeRecipeActivity
            subwayOnProcess = SubwayOnProcess
        }
    }

}
