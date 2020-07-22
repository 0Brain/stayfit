
package com.zenith.stayfit.ui.supplements.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.zenith.stayfit.ui.supplements.model.Supplement

class SupplementsViewModel : ViewModel() {

    val items: LiveData<List<Supplement>> =
        MutableLiveData<List<Supplement>>().apply { value = geAllSuppleemnts() }

    fun getSupplements(): LiveData<List<Supplement>> {
        return items
    }

    private fun geAllSuppleemnts(): List<Supplement> {
        val allSupplement = mutableListOf<Supplement>()

        allSupplement.add(Supplement("Creatine", "Creatine is probably the single best supplement for muscle gain. Many studies have confirmed that it can help increase muscle mass.", "Creatine is a molecule that’s produced naturally in your body. It provides energy for your muscles and other tissues.Taking it as a dietary supplement can increase muscle creatine content by up to 40% beyond its normal levels"))
        allSupplement.add(Supplement("Protein Supplements", "Consuming enough protein is absolutely essential for optimal muscle gain. However, if you are getting enough protein in your diet, taking a protein supplement is unnecessary.", "There are many different protein supplements available, but some of the most popular are whey, casein and soy protein. Other protein supplements contain protein isolated from eggs, beef, chicken or other sources"))
        allSupplement.add(Supplement("Branched-Chain Amino Acids(BCAA)", "Branched-chain amino acids are important for muscle growth. They are found in many foods, and it is unclear if taking them as a supplement is helpful when you already consume enough protein.", "Branched-chain amino acids (BCAAs) consist of three individual amino acids: leucine, isoleucine and valine.They are found in most protein sources, particularly those of animal origin like meat, poultry, eggs, dairy and fish.BCAAs are critically important for muscle growth and make up about 14% of the amino acids in your muscles"))
        allSupplement.add(Supplement("Beta-Alanine", "Beta-alanine is an amino acid that can improve exercise performance. Some evidence shows that it may also help increase muscle mass in response to exercise", "Beta-alanine is an amino acid that reduces fatigue and may increase exercise performance Additionally, beta-alanine may help increase muscle mass if you are following an exercise program."))

        return allSupplement
    }
}
