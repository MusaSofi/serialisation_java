# serialisation_java
# Article Management Project

## Overview

This project is an application for managing media articles of an online shop. It includes abstract and concrete classes, an interface, and a CLI for interacting with the article management system.

## Abstract Class Article

The `Article` class serves as the base class for storing information about articles. It includes properties such as Id, Title, Release year, Publisher, and Base price. The class implements methods to calculate the age of the article and determine its price based on a discount.

## Classes Book and DVD

The concrete subclasses `Book` and `DVD` extend the `Article` class. They introduce additional properties such as the number of pages for books and length and age rating for DVDs. Discounts for books are based on age and page count, while DVD discounts depend on age rating.

## Interface ArticleDAO

The `ArticleDAO` interface defines methods for storing, retrieving, and deleting articles, providing an abstraction for data access. It includes methods to get the article list, get a specific article, save an article, and delete an article.

## Class SerializedArticleDAO

The `SerializedArticleDAO` class implements the `ArticleDAO` interface using Java Object Serialization for persistent storage of article data in a file.

## Class ArticleManagement

The `ArticleManagement` class contains the business logic for managing articles. It includes methods to retrieve article data, add new articles, delete articles, and calculate statistics such as the total number of articles, books, DVDs, and the mean price of all articles.

## Class ArticleCLI

The `ArticleCLI` class provides a command-line interface for interacting with the article management system. It supports commands for adding, listing, deleting, counting, calculating mean price, and determining the oldest articles.

## Usage

To run the program, use the following command:

```bash
java ArticleCLI <file> <command>
