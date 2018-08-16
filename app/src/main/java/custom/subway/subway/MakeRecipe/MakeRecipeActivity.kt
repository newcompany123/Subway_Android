package custom.subway.subway.MakeRecipe

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import custom.subway.subway.Menu.MenuViewModel
import custom.subway.subway.Model.SubwayList
import custom.subway.subway.R
import custom.subway.subway.databinding.ActivityMakeRecipeBinding

class MakeRecipeActivity : AppCompatActivity(),MakeRecipeContract{

    lateinit var makeRecipeViewModel: MakeRecipeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initDataBinding()
    }


    fun initDataBinding() {
        makeRecipeViewModel = MakeRecipeViewModel()
        val binding: ActivityMakeRecipeBinding = DataBindingUtil.setContentView(this, R.layout.activity_make_recipe)
        binding.makeViewModel = makeRecipeViewModel
        binding.bottomMenu!!.menuViewModel = MenuViewModel.getInstance(application)
    }

    override fun showSubwayList(subwayList: SubwayList) {

    }
}
