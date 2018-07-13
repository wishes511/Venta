/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
function to_searchprod() {
                var catalogo = $('#catalogo').val();
                var uso = "buscar";
                $.ajax({
                    type: 'post',
                    data: {p: catalogo, uso: uso},
                    url: '../Productos',
                    success: function (result) {
                        $('#distribucion').html(result);
                    }
                });
            }
            function averdato() {
                var total = document.getElementsByTagName("td").length;
                for (var i = 0; i < total; i++) {
                    var variable = document.getElementsByTagName("td")[i].innerHTML;
                    alert(variable);
                }
            }


