package main

import "fmt"

func main() {
	mySlice := []string{"Monday", "Tuesday"}
	myOtherSlice := []string{"Wednesday", "Thursday"}
	mySlice = append(mySlice, myOtherSlice...)
	fmt.Println(mySlice)
}
