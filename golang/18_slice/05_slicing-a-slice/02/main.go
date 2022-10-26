package main

import "fmt"

func main() {
	greeting := []string{
		"Good morning",
		"Bonjour",
		"dias!",
		"Bongiorno!",
		"Ohayo!",
		"Selamat pagi!",
		"Gutten morgen!",
	}
	fmt.Println("[1:2],", greeting[1:2])
	fmt.Println("[:2],", greeting[:2])
	fmt.Println("[5:],", greeting[5:])
	fmt.Println("[:],", greeting[:])
}
