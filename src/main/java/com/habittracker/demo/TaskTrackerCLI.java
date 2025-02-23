package com.habittracker.demo;

import com.habittracker.demo.models.Task;

import java.util.Scanner;

public class TaskTrackerCLI {
	public static void main(String[] args) {
		TaskManager taskManager = new TaskManager();
		Scanner scanner = new Scanner(System.in);

		System.out.println("Welcome to Task Tracker CLI!");

		while (true) {
			System.out.println("\nChoose an action:");
			System.out.println("1. Add Task");
			System.out.println("2. List Tasks");
			System.out.println("3. Mark Task Complete");
			System.out.println("4. Remove Task");
			System.out.println("5. Edit Task");
			System.out.println("6. Exit");
			System.out.print("Enter your choice: ");

			int choice = scanner.nextInt();
			scanner.nextLine(); // Consume newline

			switch (choice) {
				case 1:
					System.out.print("Enter task description: ");
					String description = scanner.nextLine();
					taskManager.addTask(description);
					break;
				case 2:
					for (Task task : taskManager.listTasks()) {
						System.out.println(task);
					}
					break;
				case 3:
					System.out.print("Enter task ID to mark as complete: ");
					int completeTask = scanner.nextInt();
					taskManager.markTaskComplete(completeTask);
					break;
				case 4:
					System.out.print("Enter task ID to remove: ");
					int removeTask = scanner.nextInt();
					taskManager.removeTask(removeTask);
					break;
				case 5:
					System.out.print("Enter task ID to edit: ");
					int editTask = scanner.nextInt();
					scanner.nextLine();
					System.out.print("Enter the new task description: ");
					String newDescription = scanner.nextLine();
					taskManager.editTask(editTask, newDescription);
					break;
				case 6:
					System.out.println("Exiting... Goodbye!");
					scanner.close();
					System.exit(0);
					break;
				default:
					System.out.println("Invalid choice. Please try again.");
			}
		}
	}
}