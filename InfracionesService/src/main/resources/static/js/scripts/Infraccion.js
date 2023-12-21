const url = "infraccion/api/infraccion";

function save(bandera) {
    $("#modal-update").modal("hide");
    let id = $("#guardar").data("id");    
    let infraccion = {
        id: id,
        dni : $("#dni").val(),
        fecha : $("#fecha").val(),
        placa : $("#placa").val(),
        infraccion : $("#infraccion").val(),
        descripcion : $("#descripcion").val()
    };
    
    let metodo = bandera == 1 ? "POST" : "PUT";
    $.ajax({
        type: metodo,
        url: url,
        data: JSON.stringify(infraccion),
        dataType: "text",
        contentType: "application/json",
        cache: false,
        success: function (data) {
            if(data == 0){
                Swal.fire({
                    icon: 'error',
                    title: 'La infracción ya está registrada',
                    showConfirmButton: false,
                    timer: 1500
                });              
            } else {
                let texto = bandera == 1 ? "registrada": "actualizada";
                getTabla();
                Swal.fire({
                    icon: 'success',
                    title: 'Se ha '+texto+' la infracción',
                    showConfirmButton: false,
                    timer: 1500
                });
                clear();
            }
        },
    }).fail(function () {
        
    });
}

function deleteFila(id) {
    $.ajax({
        type: "DELETE",
        url: url+"/"+id,
        data: {
            id: id,
        },
        cache: false,
        timeout: 600000,
        success: function (data) {
            Swal.fire({
                icon: 'success',
                title: 'Se ha eliminado la infracción',
                showConfirmButton: false,
                timer: 1500
            });
            getTabla();
        },
    }).fail(function () {

    });

}

function getTabla() {
    $.ajax({
        type: "GET",
        url: url,
        dataType: "text",
        cache: false,
        success: function (data) {

            let t = $("#tablaInfracciones").DataTable();
            t.clear().draw(false);

            let botonera = '<button type="button" class="btn btn-warning btn-sm editar"><i class="fas fa-edit"></i> </button>' +
                '<button type="button" class="btn btn-danger btn-sm eliminar"><i class="fas fa-trash"></i></button>';

            let js = JSON.parse(data);

            $.each(js, function (a, b) {
                t.row.add([b.id, b.dni, b.fecha, b.placa, b.infraccion, b.descripcion, botonera]);

            });

            t.draw(false);

        },
    }).fail(function () {

    });
}

function getFila(id) {
    $.ajax({
        type: "GET",
        url: url +"/"+id,
        data: {
            id: id,
        },
        cache: false,
        timeout: 600000,
        success: function (data) {
            $("#modal-title").text("Editar Infracción");
            $("#dni").val(data.dni);
            $("#fecha").val(data.fecha);
            $("#placa").val(data.placa);
            $("#infraccion").val(data.infraccion);
            $("#descripcion").val(data.descripcion);
            $("#guardar").data("id", data.id);
            $("#guardar").data("bandera", 0);
            $("#modal-update").modal("show");
        },
    }).fail(function () {

    });
}

function clear() {
    $("#modal-title").text("Nueva infracción");
    $("#dni").val("");
    $("#fecha").val("");
    $("#placa").val("");
    $("#infraccion").val("");
    $("#descripcion").val("");
    $("#guardar").data("id", 0);
    $("#guardar").data("bandera", 1);
}

$(document).ready(function () {

    $("#tablaInfracciones").DataTable({
        language: {
            lengthMenu: "Mostrar _MENU_ registros",
            zeroRecords: "No se encontraron coincidencias",
            info: "Mostrando del _START_ al _END_ DE _TOTAL_",
            infoEmpty: "Sin resultados",
            search: "Buscar: ",
            paginate: {
                first: "Primero",
                last: "Último",
                next: "Siguiente",
                previous: "Anterior",
            },
        },
        columnDefs: [
            { targets: 0, width: "10%" },
            { targets: 1, width: "10%" },
            { targets: 2, width: "15%" },
            { targets: 3, width: "15%" },
            { targets: 4, width: "20%" },
            { targets: 5, width: "20%" },
            { targets: 6, orderable: false, width: "10%" }
        ],
    });

    clear();

    $("#nuevo").click(function () {
        clear();
    });

    $("#guardar").click(function () {

        let bandera = $("#guardar").data("bandera");
        save(bandera);
    });

    $(document).on('click', '.eliminar', function () {
        Swal.fire({
            title: 'Eliminar Infracción',
            text: "¿Está seguro de querer eliminar esta infracción?",
            icon: 'warning',
            showCancelButton: true,
            confirmButtonColor: '#3085d6',
            cancelButtonColor: '#d33',
            confirmButtonText: 'Si'
        }).then((result) => {
            if (result.isConfirmed) {
                let id = $($(this).parents('tr')[0].children[0]).text();
                deleteFila(id);
            }
        });
    });

    $(document).on('click', '.editar', function () {
        let id = $($(this).parents('tr')[0].children[0]).text();
        getFila(id);
    });

    getTabla();
});
