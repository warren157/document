package main

import "fmt"

func main() {
	greeting := make([]string, 3, 5)
	greeting[0] = "Good morning!"
	greeting[1] = "Bonjour!"
	greeting[2] = "buenos dias!"
	greeting[3] = "suprabadham" //run exception because geeting length is 3 ,index out of range[3]

	fmt.Println(greeting[2])
}
