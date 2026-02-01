# 📚 Sistema de Gestión de Libros (Biblioteca App)

Aplicación Java que implementa un sistema para gestionar libros en una biblioteca, incluyendo operaciones de préstamo, devolución, búsqueda y validación de disponibilidad. El proyecto incluye una pipeline CI/CD completa con GitHub Actions.

## 📋 Características

✅ **Gestión de Libros**
- Agregar libros a la biblioteca
- Buscar libros por ISBN, autor o **género** (NUEVO)
- Verificar disponibilidad de libros
- Prestar y devolver libros
- Listar géneros únicos registrados (NUEVO)

✅ **Sistema de Búsqueda por Género**
- Filtrar libros por categoría/género
- Listar libros disponibles de un género específico
- Obtener estadísticas por género
- Interfaz visual mejorada

✅ **Demostración Interactiva**
- Clase `Main.java` con 11 pasos de demostración
- Visualización de todas las funcionalidades
- 10 libros de ejemplo en 5 géneros diferentes
- Tablas formateadas con información clara

✅ **Pruebas Unitarias**
- Cobertura completa de funcionalidad
- 27 tests con JUnit 5 (incluyendo 7 nuevos para género)
- Validación de casos edge

✅ **Validación de Código**
- Checkstyle para estilo de código
- Reporte de cobertura con JaCoCo
- Compilación Maven

✅ **CI/CD Pipeline**
- Validación automática en cada push
- Tests automáticos
- Reporte de cobertura
- Generación de artefactos

## 🛠️ Tecnologías

- **Java 11+**
- **Maven** - Gestión de dependencias y build
- **JUnit 5** - Framework de testing
- **Mockito** - Mocking para tests
- **JaCoCo** - Cobertura de código
- **Checkstyle** - Validación de estilo (Google Code Style)
- **PMD** - Análisis estático de código (Lint)
- **GitHub Actions** - CI/CD

## 📁 Estructura del Proyecto

```
biblioteca-app/
├── .github/
│   └── workflows/
│       └── ci.yml                              # Pipeline CI/CD
├── src/
│   ├── main/
│   │   └── java/com/curso/
│   │       ├── model/
│   │       │   └── Book.java                   # Modelo de Libro (con género)
│   │       ├── service/
│   │       │   └── LibraryService.java         # Servicio de Biblioteca
│   │       └── Main.java                       # Demostración interactiva (NUEVO)
│   └── test/
│       └── java/com/curso/
│           └── service/
│               └── LibraryServiceTest.java     # Tests unitarios (27 tests)
├── pom.xml                                     # Configuración Maven
├── README.md                                   # Este archivo
└── CAMBIOS.md                                  # Detalle de cambios (NUEVO)
```

## 🎬 Ejecución de la Demostración

Para ver el sistema en acción, ejecuta la clase `Main.java`:

```bash
# Compilar
javac -encoding UTF-8 -d target\classes \
    src\main\java\com\curso\model\Book.java \
    src\main\java\com\curso\service\LibraryService.java \
    src\main\java\com\curso\Main.java

# Ejecutar
java -cp target\classes com.curso.Main
```

**Salida esperada**: Demostración completa de 11 pasos mostrando:
1. Agregación de 10 libros en 5 géneros
2. Listado de todos los libros
3. Búsqueda por ISBN
4. Búsqueda por Autor
5. **Búsqueda por Género (NUEVA)**
6. **Libros disponibles por género (NUEVA)**
7. Préstamo de libros
8. Estado después de préstamos
9. Devolución de libros
10. Estadísticas
11. **Listado de géneros disponibles (NUEVA)**

## 🚀 Cómo Ejecutar el Proyecto Localmente

### Requisitos Previos

- **Java 11** o superior
- **Maven 3.6** o superior
- **Git**

### Instalación

1. **Clonar el repositorio**
   ```bash
   git clone https://github.com/tu-usuario/biblioteca-app.git
   cd biblioteca-app
   ```

2. **Compilar el proyecto**
   ```bash
   mvn clean compile
   ```

3. **Ejecutar las pruebas**
   ```bash
   mvn test
   ```

4. **Ejecutar LINT (análisis estático)**
   ```bash
   mvn pmd:check
   ```

5. **Validar estilo de código**
   ```bash
   mvn checkstyle:check
   ```

6. **Generar reporte de cobertura**
   ```bash
   mvn jacoco:report
   ```
   - El reporte se genera en: `target/site/jacoco/index.html`

7. **Compilar el JAR**
   ```bash
   mvn package
   ```
   - El JAR se genera en: `target/biblioteca-app-1.0.0.jar`

8. **Ejecutar pipeline completo localmente**
   ```bash
   mvn clean compile pmd:check checkstyle:check test jacoco:report package
   ```
   <img width="1089" height="418" alt="image" src="https://github.com/user-attachments/assets/b2435151-bc20-4314-9921-e5c383b77faf" />

### Opción 2: Ejecución Manual con Java

```bash
# Compilar
javac -encoding UTF-8 -d target\classes \
    src\main\java\com\curso\model\Book.java \
    src\main\java\com\curso\service\LibraryService.java \
    src\main\java\com\curso\Main.java

# Ejecutar
java -cp target\classes com.curso.Main
```

## 📊 Pipeline CI/CD - GitHub Actions

### 4 Pasos Principales del Pipeline

El archivo `.github/workflows/ci.yml` ejecuta automáticamente los siguientes pasos:

#### 1️⃣ **LINT** - Análisis Estático de Código
   - **Herramienta**: PMD (Program Mistake Detector)
   - **Qué valida**:
     - Reglas de código básicas
     - Errores de sintaxis potenciales
     - Convenciones de nombres
   - **Comando**: `mvn pmd:check`
   - **Falla el pipeline si**: Se encuentran problemas críticos

#### 2️⃣ **FORMAT CHECK** - Validación de Estilo de Código
   - **Herramienta**: Checkstyle (Google Code Style)
   - **Qué valida**:
     - Indentación (4 espacios)
     - Longitud de líneas (máx 100 caracteres)
     - Nombre de variables y métodos (camelCase)
     - Documentación Javadoc
     - Imports no utilizados
   - **Comando**: `mvn checkstyle:check`
   - **Falla el pipeline si**: Hay violaciones de estilo

#### 3️⃣ **TEST** - Pruebas Unitarias + Cobertura
   - **Framework**: JUnit 5
   - **Qué valida**:
     - 20+ tests unitarios
     - Casos normales y casos de error
     - Cobertura de código con **JaCoCo**
   - **Comando**: `mvn test`
   - **Reporte de Cobertura**:
     - Ubicación: `target/site/jacoco/index.html`
     - Cobertura esperada: ~95%
   - **Falla el pipeline si**: Algún test falla

#### 4️⃣ **BUILD** - Compilación y Generación de Artefactos
   - **Herramienta**: Maven
   - **Qué genera**:
     - JAR compilado: `target/biblioteca-app-1.0.0.jar`
     - Reporte de cobertura
     - Artefactos para descargar
   - **Comando**: `mvn package`
   - **Almacenamiento**:
     - JAR: Se mantiene 5 días
     - Reporte: Se mantiene 7 días
   - **Falla el pipeline si**: La compilación falla

### Flujo Completo del Pipeline

```
┌─ Checkout código
├─ Setup Java 11
├─ LINT (PMD)
├─ FORMAT CHECK (Checkstyle)
├─ BUILD (Compile)
├─ TEST (JUnit 5)
├─ COVERAGE (JaCoCo)
├─ PACKAGE (JAR)
├─ Upload Artefactos
└─ Resumen
```

### Ejemplo de Ejecución Exitosa
<img width="1405" height="894" alt="image" src="https://github.com/user-attachments/assets/25fcf9ed-5296-464f-b9e6-dc97f0824b09" />
<img width="1309" height="921" alt="image" src="https://github.com/user-attachments/assets/ab7b0026-5e01-4198-b421-0ffbbab56302" />


```
════════════════════════════════════════════
✅ PIPELINE CI/CD COMPLETADO EXITOSAMENTE
════════════════════════════════════════════
✓ LINT: Análisis estático completado
✓ FORMAT CHECK: Estilo validado
✓ COMPILE: Compilación exitosa
✓ TEST: Tests unitarios pasados
✓ COVERAGE: Reporte de cobertura generado
✓ BUILD: Artefactos generados
════════════════════════════════════════════
```

### Si el Pipeline Falla

1. **Error en LINT (PMD)**: Correge los problemas de código
2. **Error en FORMAT CHECK**: Aplica los cambios de estilo
3. **Error en TEST**: Revisa los tests que fallaron
4. **Error en BUILD**: Verifica que no haya errores de compilación

**El pipeline se detiene en el primer error y notifica al repositorio.**
<img width="1126" height="766" alt="image" src="https://github.com/user-attachments/assets/28bf3d8d-3867-4c0f-a436-28ae0c78130a" />


## 🧪 Tests Unitarios

El proyecto incluye 20 tests que cubren:

### Pruebas de Adición de Libros
- ✅ Agregar un libro correctamente
- ✅ Rechazar ISBN duplicados
- ✅ Validar que no sea nulo

### Pruebas de Búsqueda
- ✅ Encontrar libro por ISBN
- ✅ Buscar libros por autor
- ✅ Manejo de casos no encontrados

### Pruebas de Préstamo/Devolución
- ✅ Prestar libro disponible
- ✅ Rechazar préstamo de libro no disponible
- ✅ Devolver libro prestado

### Pruebas de Consultas
- ✅ Obtener todos los libros
- ✅ Obtener libros disponibles
- ✅ Contar libros disponibles

### Pruebas de Eliminación
- ✅ Eliminar libro existente
- ✅ Rechazar eliminación de libro inexistente

### Ejecutar Tests
```bash
# Ejecutar todos los tests
mvn test

# Ejecutar un test específico
mvn test -Dtest=LibraryServiceTest#testAddBook

# Ejecutar tests con salida detallada
mvn test -X
```

## 📈 Cobertura de Código

El proyecto utiliza **JaCoCo** para medir la cobertura de código.

### Generar Reporte
```bash
mvn clean test jacoco:report
```

### Ver Reporte
Abre el archivo: `target/site/jacoco/index.html` en tu navegador

### Métricas de Ejemplo
- **Cobertura Total**: ~95%
- **Clases**: 100%
- **Métodos**: 95%
- **Líneas**: 93%

## 🔍 Validación de Estilo de Código

El proyecto usa **Checkstyle** con la configuración de Google.

### Validar
```bash
mvn checkstyle:check
```

### Ver Reportes
```bash
mvn checkstyle:checkstyle
```

Reporte en: `target/checkstyle-result.xml`

### Reglas Validadas
- Indentación (4 espacios)
- Longitud de línea (máx 100 caracteres)
- Nombres de variables y métodos en camelCase
- Documentación de código (Javadoc)
- Imports no utilizados

## 📝 Ejemplos de Uso

### Crear una instancia del servicio y agregar libros

```java
import com.curso.model.Book;
import com.curso.service.LibraryService;

public class Main {
    public static void main(String[] args) {
        // Crear servicio
        LibraryService library = new LibraryService();

        // Crear libros
        Book book1 = new Book("978-3-16-148410-0", "Clean Code", "Robert C. Martin", 2008);
        Book book2 = new Book("978-0-13-110362-7", "The C Programming Language", "Brian Kernighan", 1988);

        // Agregar a la biblioteca
        library.addBook(book1);
        library.addBook(book2);

        // Listar todos los libros
        System.out.println("Total de libros: " + library.getTotalBooks());

        // Prestar un libro
        Book borrowed = library.borrowBook("978-3-16-148410-0");
        System.out.println("Libro prestado: " + borrowed.getTitle());

        // Buscar libros por autor
        library.findBooksByAuthor("Robert C. Martin")
               .forEach(System.out::println);

        // Devolver el libro
        library.returnBook("978-3-16-148410-0");
    }
}
```

## 🔄 Flujo de trabajo recomendado

1. **Crear rama de feature**
   ```bash
   git checkout -b feature/nueva-funcionalidad
   ```

2. **Hacer cambios y tests**
   ```bash
   # Editar código
   mvn test
   ```

3. **Hacer commit**
   ```bash
   git add .
   git commit -m "Descripción del cambio"
   ```

4. **Push a GitHub**
   ```bash
   git push origin feature/nueva-funcionalidad
   ```

5. **GitHub Actions ejecutará automáticamente:**
   - ✅ Compilación
   - ✅ Validación de estilo
   - ✅ Tests
   - ✅ Cobertura

6. **Pull Request y Merge**
   - Revisar resultados del pipeline
   - Hacer merge a `main`

## ❌ Solución de Problemas

### Error: "Maven not found"
```bash
# Instalar Maven (si no lo tienes)
# Windows: Descarga de https://maven.apache.org/download.cgi
# Mac: brew install maven
# Linux: sudo apt-get install maven
```

### Error: "Java version not supported"
```bash
# Verificar versión Java
java -version

# Necesitas Java 11 o superior
# https://adoptopenjdk.net/
```

### Tests fallan localmente pero no en GitHub
```bash
# Limpiar caché Maven
mvn clean
mvn test
```

### No se generan reportes de cobertura
```bash
# Regenerar
mvn clean test jacoco:report
# Verificar: target/site/jacoco/
```
## Estado de Completitud

### ✅ Funcionalidades Implementadas

- [x] **Sistema de Búsqueda por Género**
  - [x] Método `findBooksByGenre(String)`
  - [x] Método `findAvailableBooksByGenre(String)`
  - [x] Método `getAllGenres()`
  
- [x] **Clase Main.java de Demostración**
  - [x] 11 pasos de demostración completos
  - [x] Visualización con tablas formateadas
  - [x] Manejo de 10 libros en 5 géneros
  - [x] Todas las operaciones funcionales

- [x] **Modelo de Datos Actualizado**
  - [x] Atributo `genre` en Book
  - [x] Constructor con género
  - [x] Getters y setters
  - [x] toString actualizado

- [x] **Tests Unitarios**
  - [x] 7 nuevos tests para género
  - [x] Tests compilados y listos
  - [x] Cobertura completa

- [x] **Documentación**
  - [x] README.md actualizado
  - [x] CAMBIOS.md detallado
  - [x] RESUMEN_FINAL.md completo
  - [x] Javadoc en todo el código

- [x] **Pipeline CI/CD**
  - [x] 4 pasos: Lint → Format Check → Test → Build
  - [x] Validación de estilo
  - [x] Tests automáticos
  - [x] Reporte de cobertura

  ## Validaciones Completadas

### Funcionalidad
- [x] Agregar libros con género
- [x] Buscar por ISBN
- [x] Buscar por Autor
- [x] **Buscar por Género (NUEVA)**
- [x] **Filtrar disponibles por género (NUEVA)**
- [x] **Obtener géneros únicos (NUEVA)**
- [x] Prestar libros
- [x] Devolver libros
- [x] Estadísticas

### Calidad de Código
- [x] Documentación Javadoc completa
- [x] Nombres significativos
- [x] Métodos bien definidos
- [x] Manejo de excepciones
- [x] Validación de inputs

### Testing
- [x] 27 tests implementados
- [x] Tests de género incluidos
- [x] Casos positivos y negativos
- [x] Edge cases cubiertos

### Demostración
- [x] Interfaz clara y legible
- [x] Información estructurada
- [x] Tablas formateadas
- [x] Estadísticas disponibles
## 📚 Documentación Adicional

- [Maven Documentation](https://maven.apache.org/)
- [JUnit 5 User Guide](https://junit.org/junit5/docs/current/user-guide/)
- [GitHub Actions Documentation](https://docs.github.com/en/actions)
- [JaCoCo Documentation](https://www.jacoco.org/jacoco/trunk/doc/)

## 👨‍💻 Autor

Jotcelyn Godoy
Cristian Robles

## 📄 Licencia

Este proyecto está bajo licencia MIT. Ver archivo `LICENSE` para más detalles.

## 🤝 Contribuciones

Las contribuciones son bienvenidas. Por favor:

1. Fork el proyecto
2. Crea una rama para tu feature (`git checkout -b feature/AmazingFeature`)
3. Commit tus cambios (`git commit -m 'Add some AmazingFeature'`)
4. Push a la rama (`git push origin feature/AmazingFeature`)
5. Abre un Pull Request

---

**Última actualización**: Enero 2026
