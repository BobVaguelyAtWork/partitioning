package com.example

import spock.lang.Specification
import static com.example.partitioning.partitionBy

class PartitioningSpec extends Specification {

    def '''partitioning an empty list returns an empty list'''() {
        expect:
        partitionBy([]) == []
    }

    def '''partitioning on identity returns lists of consecutive duplicates'''() {
        expect:
        partitionBy([4, 1, 1, 4, 3, 3]) == [[4], [1, 1], [4], [3, 3]]
    }

    def '''partitioning with a closure `f` groups consecutive elements where f(element) is equal'''() {
        expect:
        partitionBy([8, -4, 4, 2, -3, 2, 1, -1, -1]) { Math.abs(it) } == [[8], [-4, 4], [2], [-3], [2], [1, -1, -1]]
    }

    def '''partition can use map fields'''() {
        given:
        def people = [
            [fName: "Homer", lName: "Simpson"],
            [fName: "Marge", lName: "Simpson"],
            [fName: "Ned", lName: "Flanders"],
            [fName: "Maude", lName: "Flanders"],
            [fName: "Lisa", lName: "Simpson"],
            [fName: "Bart", lName: "Simpson"],
            [fName: "Maggie", lName: "Simpson"],
            [fName: 'Krusty'],
            [fName: "Rod", lName: "Flanders"],
            [fName: "Todd", lName: "Flanders"]
        ]
        expect:
        partitionBy(people) { it.lName } == [
            [[fName: 'Homer', lName: 'Simpson'],
             [fName: 'Marge', lName: 'Simpson']],
            [[fName: 'Ned', lName: 'Flanders'],
             [fName: 'Maude', lName: 'Flanders']],
            [[fName: 'Lisa', lName: 'Simpson'],
             [fName: 'Bart', lName: 'Simpson'],
             [fName: 'Maggie', lName: 'Simpson']],
            [[fName: 'Krusty']],
            [[fName: 'Rod', lName: 'Flanders'],
             [fName: 'Todd', lName: 'Flanders']]]
    }
}
