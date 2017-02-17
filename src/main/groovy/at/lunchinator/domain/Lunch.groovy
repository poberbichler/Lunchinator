package at.lunchinator.domain

import groovy.transform.ToString

@ToString(includeNames = true)
class Lunch {
	String id
	String name
	String restaurant
	String restaurantUrl
}
