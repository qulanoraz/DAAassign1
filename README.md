# Project Report: Assignment 1

## Overview  
This project implements classic divide-and-conquer algorithms in Java, focusing on safe recursion patterns and performance analysis. The algorithms covered include MergeSort, QuickSort, Deterministic Select (Median-of-Medians), and Closest Pair of Points.

## Objectives  
- Implement efficient, recursive versions of these algorithms.  
- Collect runtime metrics: execution time, recursion depth, number of comparisons, and allocations.  
- Analyze theoretical running-time recurrences using Master Theorem and Akra-Bazzi intuition, validating results against empirical data.  
- Maintain a clean and structured Git workflow with well-defined feature branches and clear commit messages.

## Algorithmic Analysis  
- **MergeSort** has a recurrence $$T(n) = 2T(n/2) + \Theta(n)$$ resulting in a time complexity of $$\Theta(n \log n)$$.  
- **QuickSort** uses randomized pivots and a recursion strategy that typically bounds stack depth to $$\mathcal{O}(\log n)$$, with expected $$\Theta(n \log n)$$ average time.  
- **Deterministic Select** operates in linear time $$\Theta(n)$$ by choosing a pivot via the Median-of-Medians approach, ensuring balanced partitions.  
- **Closest Pair** applies recursive splitting and scanning in a strip, with a worst-case complexity of $$\Theta(n \log n)$$.

## Metrics and Validation  
Performance metrics are gathered through a dedicated `Metrics` class counting comparisons, allocations, and tracking recursion depth. Measurements align well with theoretical expectations, and discrepancies are discussed in terms of constant factors such as caching and garbage collection.

## Project Structure and Workflow  
The project follows a disciplined Git workflow with feature branches dedicated to each algorithm and functionality. Commits adhere to a conventional format, ensuring clarity and ease of navigation.

## Conclusion  
The project successfully demonstrates the implementation and analysis of fundamental divide-and-conquer algorithms. The empirical data supports theoretical complexity results, providing valuable insights into their practical efficiency. Further extension can focus on edge case handling and optimization refinements.
