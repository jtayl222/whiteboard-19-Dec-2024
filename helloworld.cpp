#include <iostream>
#include <string>
using namespace std;

// Node structure
struct Node {
    string name;
    Node* nextNode;
    Node* prevNode;
};

// Circular doubly linked list class
class CircularDoublyLinkedList {
private:
    Node* top=nullptr;

public:
    // Constructor
    template <size_t N>
    CircularDoublyLinkedList(string (&nameList)[N]) {        
        // ToDo: validate nameList imnput
        // cout << "adding items list starting with " << nameList[0] << endl;

        // Start by putting first name in the list at the top
        top = new Node();
        top->name = nameList[0];
        top->nextNode = top;
        top->prevNode = top;

        // push every additional item onto the top of the list
        bool firstItem=true;
        for (string name : nameList)
        {
            if (firstItem==true) { // First name is already at top
                firstItem=false;
                continue;
            }
            top = insertNewNodeBefore(top, name);
            //displayAllNodeNames();
        }
    }

    string removeEveryNthNode(int nth) {
        Node* currentNode = top;
        while(currentNode->nextNode != currentNode) {
            // displayAllNodeNames();
            cout << "SKIP: ";
            for (int i=1; i<nth; i++) {
                cout << currentNode->name << " ";
                currentNode = currentNode->nextNode;
            }
            cout << "REMOVE: " << currentNode->name << endl ;
            currentNode = removeNode(currentNode);
        }
        return currentNode->name;
    }

    Node* removeNode(Node *n) {
        n->nextNode->prevNode = n->prevNode;
        n->prevNode->nextNode = n->nextNode;
        Node* ret = n->nextNode;
        if (top==n) {
            top=ret;
        }
        delete n;
        return ret;
    }

    Node* insertNewNodeBefore(Node *&existingNode, string newName)
    {
        Node *newNode = new Node();
        newNode->name = newName;
        newNode->nextNode = existingNode;
        newNode->prevNode = existingNode->prevNode;
        existingNode->prevNode->nextNode = newNode;
        existingNode->prevNode = newNode;
        return newNode;
    }

    // Function to display the names in the list
    void displayAllNodeNames() {
        if (top == nullptr) return;

        Node* currentNode = top;
        do {
            cout << currentNode->name << ", ";
            currentNode = currentNode->nextNode;
        } while (currentNode != top);
        cout << endl;
    }

    // Destructor to free memory
    ~CircularDoublyLinkedList() {
        if (!top) return;

        Node* current = top;
        Node* nextNode;
        do {
            nextNode = current->nextNode;
            delete current;
            current = nextNode;
        } while (current != top);
        top = nullptr;
    }
};

int main() {
    // Example usage
    string nameList[] = {
        "Andrea",
        "Barry",
        "Chantal",
        "Dexter",
        "Erin",
        "Fernand",
        "Gabrielle",
        "Humberto",
        "Imelda",
        "Jerry",
        "Karen",
        "Lorenzo",
        "Melissa",
        "Nestor",
        "Olga",
        "Pablo",
        "Rebekah",
        "Sebastien",
        "Tanya",
        "Van",
        "Wendy"
    };

    CircularDoublyLinkedList cdll(nameList);
    // Display the list
    // cdll.displayAllNodeNames();
    string remainingName = cdll.removeEveryNthNode(5);
    cout << "Remaining name is " << remainingName;
    return 0;
}