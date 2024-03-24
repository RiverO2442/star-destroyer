package org.rivero.roommanager.subscription

import lombok.RequiredArgsConstructor
import org.springframework.http.HttpStatusCode
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
class MealController {
    private val mealService: MealService? = null

    @get:GetMapping("/meals")
    val list: ResponseEntity<Collection<MealCheckListDto>>
        get() = ResponseEntity.ok().body(mealService!!.list)

    @GetMapping("/meals/{mealId}")
    fun getOne(@PathVariable(name = "mealId") mealId: String?): ResponseEntity<MealCheckListDto?> {
        return ResponseEntity.ok().body(mealService!!.getOne(mealId!!))
    }

    @PutMapping("/meals")
    fun insert(@RequestBody meal: MealCreateRequest?): ResponseEntity<String> {
        mealService!!.insert(meal!!)
        return ResponseEntity.status(HttpStatusCode.valueOf(201)).build()
    }
}