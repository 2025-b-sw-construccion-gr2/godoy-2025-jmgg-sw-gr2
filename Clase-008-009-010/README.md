# 🕵️ Caso de Estudio: Fallo Masivo en AWS US-EAST-1 (Diciembre 2021)

Este documento contiene el análisis detallado de un incidente de infraestructura a gran escala ocurrido en Amazon Web Services (AWS) en diciembre de 2021, utilizando los principios de la Gestión de Configuración de Software (SCM) y el Ciclo de Vida del Desarrollo de Software (SDLC).

---

## 📄 Documento Principal de Análisis

El documento central de este proyecto es:

* **`Godoy_Robles_CLASE 008-009-010.pdf`**

Este documento responde en detalle a las siguientes preguntas clave sobre el incidente.

### 🧠 Contenido del Análisis

| Sección | Descripción | Foco Principal |
| :--- | :--- | :--- |
| **Resumen del Caso** | Contexto del evento, fecha y servicios afectados. | Identificación del problema (Fuente: *Post-Mortem* de AWS). |
| **Clasificación del Mantenimiento** | Determinación del tipo de mantenimiento que causó el fallo. | Tipos: Perfectivo y Correctivo (en la fase de remediación). |
| **Procesos SCM Involucrados** | Hipótesis sobre cómo el Control de Versiones y la Gestión de Cambios (Change Management) actuaron durante la emergencia. | Aplicación de ramas de emergencia y *rollback* de configuración. |
| **Impacto en el SDLC** | Evaluación de cómo la falla afectó a las fases de Planificación, Pruebas y Despliegue. | Despliegue de emergencia y pruebas de regresión aceleradas. |
| **Beneficios del SCM** | Conclusiones sobre la importancia de la trazabilidad y el control de configuración. | Estabilidad y Reducción del Tiempo de Recuperación (TTR). |

---

## 💡 Referencia del Incidente

La caída fue causada por un **mantenimiento Perfectivo de rutina** en un sistema interno de conectividad de red, el cual introdujo un error que paralizó la comunicación interna de la región **US-EAST-1**.

**Fuente Técnica Principal:**
> El informe *post-mortem* oficial de Amazon Web Services, disponible en su Blog de Noticias, detalla la causa raíz como un comportamiento inesperado durante el despliegue de una mejora de escalabilidad.

---

## 👥 Autor(es)

Análisis realizado por:
* Jotcelyn Godoy, Cristian Robles