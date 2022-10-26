package main

import "fmt"

func main() {
	greeting := []string{
		"Good morning",
		"Bonjour!",
		"dias",
		"Ohayo",
	}

	for i, cur := range greeting {
		fmt.Println(i, cur)
	}
}
