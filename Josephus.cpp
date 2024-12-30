#include <iostream>
#include <list>
#include <string>
using namespace std;

// Function to simulate the Josephus problem
string lastPersonStanding(list<string>& people, int N) {
    auto it = people.begin();

    while (people.size() > 1) {
        // Move iterator N-1 steps forward in circular manner
        for (int i = 1; i < N; ++i) {
            ++it;
            if (it == people.end()) {
                it = people.begin();  // Wrap around if end is reached
            }
        }

        // Remove the Nth person and move to the next person
        it = people.erase(it);
        if (it == people.end()) {
            it = people.begin();  // Wrap around if end is reached
        }
    }

    // Return the name of the last remaining person
    return *people.begin();
}

int main() {
    int M, N;
    cout << "Enter the number of people at the table (M): ";
    cin >> M;

    cout << "Enter the step count (N): ";
    cin >> N;

    list<string> people;
    cout << "Enter the names of the " << M << " people:\n";
    for (int i = 0; i < M; ++i) {
        string name;
        cin >> name;
        people.push_back(name);
    }

    // Get the last remaining person
    string lastPerson = lastPersonStanding(people, N);

    cout << "The last person remaining at the table is: " << lastPerson << endl;

    return 0;
}