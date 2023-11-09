# Linear math library for computer graphics

---

This library is created for further using in computer graphics lessons to create
projects with 3D graphics, basically this library can be extrapolated to any
matrix or vector but according to task I implemented vectors and matrixes of
2, 3 and 4 dimension.

Vectors and matrixes in this library has two utility classes: factory class and
utils class that could help you in development.

By default, this library uses vector-columns.

### Vectors

---

Each vector implements following methods:

* `multiply(float number)` multiples current instance of vector on number;
* `divide(float number)` divides current instance of vector on number;
* `normalize()` normalizes current instance of vector on number;
* `set(float number, int axis)` sets value of given axis;
* `get(int axis)` returns value on given axis;
* `getLength()` returns amount of axes of vector;
* `addVector(AbstractVector vectorToAdd)` adds Vector to current instance of vector;
* `subtractVector(AbstractVector vectorToSubract)` subtracts Vector to current instance of vector;
* `getScalarMultiplication(AbstractVector vectorToMultiplyBy)` returns scalar multiplication of vectors;
* `calculateLength()` returns length of vector.

Vector3D has one extra method:
* `product(Vector3D vector)` changes current instance of vector to a product of two vectors

### Matrixes

---

Each matrix implements following methods:

* `addMatrix(AbstractMatrix matrix)` adds matrix to current instance;
* `subtractMatrix(AbstractMatrix matrix)` subtracts matrix from current instance;
* `multiply(float number)` multiplies current instance on number;
* `divide(float number)` divides current instance on number;
* `multiplyOnVector(AbstractVector vector)` returns result of multiplication of current instance by given vector;
* `transpose()` transposes current instance;
* `multiplyOnMatrix(AbstractMatrix matrix)` multiplies current instance on give matrix;
* `getVector(int number)` returns vector on given index;
* `setVector(AbstractVector vector, int number)` sets vector to given index;
* `getLength()` returns length of vectors array;
* `getVectorLength(int vector)` returns length of vector at `vector` position;
* `getMinor(int y, int x)` return minor of current instance without `x` and `y` axes;
* `getDeterminant()` returns determinant of current instance;
* `reverseMatrix()` converts current matrix to it reversed matrix;
* `getElement(int y, int x)` shortcut to `getVector(x).get(y)`;
* `setElement(float number, int y, int x)` shortcut to `getVector(x).set(number, y)`;

It's important to say that on current moment `Matrix2D` doesn't support operations
with `getMinor(int y, int x)` because there is no classes for 1 dimensional matrixes.

### Utils and Factory classes

----

Vectors and Matrixes has those classes to help you in development.

`Factory` class has static methods for creating new objects bases on some functions
invoked with given data.

`Utils` class has static methods that after invocation changes first give object.

### Exceptions

---

* `WrongDimensionMatrixException` thrown when matrixes are not compatible;
* `WrongDimensionVectorException` thrown when vectors are not compatible;
* `WrongAmountOfAxesGivenException` thrown if current version of library not supports such dimensions of objects;
* `WrongAmountOfArgumentsGivenException` basically same as `WrongAmountOfAxesGivenException` but throwed from constructor of matrix with float collection;
* `ReversingMatrixException` throws from `reverse()` funtions if matrix doesn't have reversed matrix (determinant = 0).
