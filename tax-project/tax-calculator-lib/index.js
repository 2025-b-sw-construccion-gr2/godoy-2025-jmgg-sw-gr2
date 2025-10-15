/**
 * tax-calculator-lib/index.js
 *
 * Módulo de la biblioteca para calcular impuestos.
 * Exporta funciones esenciales para la aplicación principal.
 */

// Tasa de Impuesto General al Valor (ej. 15%)
const TASA_IVA_GENERAL = 0.15;

// Tasa de Impuesto al Consumo Específico (ej. 5%)
const TASA_ICE_ESPECIFICO = 0.05;

/**
 * Función que calcula el monto de IVA a pagar.
 * @param {number} montoBase - El precio base o subtotal.
 * @param {number} tasa - La tasa de IVA a aplicar (por defecto 0.15).
 * @returns {number} El valor del IVA calculado.
 */
function calcularIVA(montoBase, tasa = TASA_IVA_GENERAL) {
    if (typeof montoBase !== 'number' || montoBase <= 0) {
        return 0;
    }
    // Redondeamos a dos decimales para simular valores monetarios
    return parseFloat((montoBase * tasa).toFixed(2));
}

/**
 * Función que calcula el monto total a pagar (Base + IVA).
 * @param {number} montoBase - El precio base o subtotal.
 * @returns {number} El monto total con IVA incluido.
 */
function calcularTotalConIVA(montoBase) {
    const iva = calcularIVA(montoBase);
    // Redondeamos a dos decimales
    return parseFloat((montoBase + iva).toFixed(2));
}

/**
 * Función que calcula un impuesto específico (ICE) sobre un monto base.
 * @param {number} montoBase - El precio base o subtotal.
 * @param {number} tasa - La tasa de impuesto específico (por defecto 0.05).
 * @returns {number} El valor del impuesto específico calculado.
 */
function calcularICE(montoBase, tasa = TASA_ICE_ESPECIFICO) {
    if (typeof montoBase !== 'number' || montoBase <= 0) {
        return 0;
    }
    return parseFloat((montoBase * tasa).toFixed(2));
}

// Exportamos las funciones en un objeto para que puedan ser utilizadas
// por otros módulos (nuestro proyecto principal).
module.exports = {
    calcularIVA,
    calcularTotalConIVA,
    calcularICE,
    // Exportamos las tasas como constantes por si se necesitan
    TASA_IVA_GENERAL,
    TASA_ICE_ESPECIFICO
};