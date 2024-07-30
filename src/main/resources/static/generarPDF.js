function obtenerDatosDesdeLocalStorage() {
    const vehicleDataString = localStorage.getItem('vehicleData');
    if (vehicleDataString) {
        // Parsear el JSON almacenado en localStorage
        return JSON.parse(vehicleDataString);
    }
    return {}; // Retornar un objeto vacío si no hay datos
}

async function enviarDatosAlBackend() {
    const datos = obtenerDatosDesdeLocalStorage();
    console.log("Datos enviados:", datos); // Verifica los datos en la consola

    try {
        const response = await fetch('https://r5soat-faf3abc78f43.herokuapp.com/soat/documento', { // URL completa
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(datos)
        });

        if (!response.ok) {
            throw new Error(`Error ${response.status}: ${response.statusText}`);
        }

        // Obtener el blob del PDF desde la respuesta
        const blob = await response.blob();

        // Crear una URL para el blob
        const url = URL.createObjectURL(blob);

        // Abrir el PDF en una nueva pestaña
        window.open(url, '_blank');

        // Liberar la URL del blob después de abrir
        URL.revokeObjectURL(url);

    } catch (error) {
        console.error('Error al enviar los datos:', error);
    }
}

// Llamar a la función para enviar los datos
enviarDatosAlBackend();
