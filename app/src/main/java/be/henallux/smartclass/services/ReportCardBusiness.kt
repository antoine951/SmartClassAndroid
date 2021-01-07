package be.henallux.smartclass.services

import be.henallux.smartclass.model.Result

class ReportCardBusiness (results: ArrayList<Result>) {
    val french : ArrayList<Result> = ArrayList()
    val math : ArrayList<Result> = ArrayList()
    val sciences : ArrayList<Result> = ArrayList()
    val other : ArrayList<Result> = ArrayList()

    init{
        sortResult(results)
    }

    private fun sortResult(results: ArrayList<Result>) {
        for (res in results) {
            when (res.schoolSubject) {
                "Français" -> french.add(res)
                "Mathématiques" -> math.add(res)
                "Eveil" -> sciences.add(res)
                else -> other.add(res)
            }
        }
    }
}