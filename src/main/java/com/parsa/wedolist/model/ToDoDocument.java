package com.parsa.wedolist.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;

@Document(collection = "ToDoLists")
@Setter @Getter
@AllArgsConstructor
@NoArgsConstructor
public class ToDoDocument {
    @Id
    private String id;
    private ArrayList<TodoItem> items;
    private ArrayList<TodoState> states;
    private ArrayList<String> removed;

    public void merge(ToDoDocument other) {
        // union removed
        for (String removedId : other.removed) {
            if (!this.removed.contains(removedId)) {
                this.removed.add(removedId);
            }
        }

        // union items
        for (TodoItem item : other.items) {
            boolean found = false;
            for (TodoItem thisItem : this.items) {
                if (thisItem.getId().equals(item.getId())) {
                    found = true;
                    break;
                }
            }
            if (!found) {
                this.items.add(item);
            }
        }

        // remove items if they are in removed
        for (String removedId : this.removed) {
            this.items.removeIf(item -> item.getId().equals(removedId));
        }


        // add all states
        this.states.addAll(other.states);

        // remove duplicates using LLW
        this.states.sort(TodoState::compareTo);
        ArrayList<TodoState> uniqueStates = new ArrayList<>();
        for (TodoState state : this.states) {
            if (uniqueStates.isEmpty() || !uniqueStates.get(uniqueStates.size() - 1).getId().equals(state.getId()))
                uniqueStates.add(state);
        }
    }
}

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
class TodoItem {
    private String id;
    private String title;
}

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
class TodoState implements Comparable<TodoState> {
    private String id;
    private boolean completed;
    private long timestamp;


    @Override
    public int compareTo(TodoState o) {
         if (this.timestamp == o.timestamp) {
             // if timestamps are equal, we want to make sure that completed states come first
             return this.completed ? 1 : -1;
         }
         return Long.compare(this.timestamp, o.timestamp);
    }
}