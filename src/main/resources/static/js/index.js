const baseURL = $('#baseURL').val();

$(function() {

	let bookmarkMap = new Map();

	let dataTable = $('#dataTable')
			.DataTable(
					{
						"ajax" : function(data, callback) {
							let pageSize = data.length;
							let currentPage = data.start / data.length;
							$.ajax({
								url : baseURL + "/list",
								data : {
									bookmark : bookmarkMap[currentPage],
									pageSize : pageSize,
									currentPage : currentPage,
									type : $('#type').val()
								}
							}).then(function success(res) {
								bookmarkMap[currentPage + 1] = res.bookmark;
								if (res.data) {
									for (var i = 0; i < res.data.length; i++) {
										res.data[i].index = i + 1;
									}
								}
								callback(res);
							}, function fail(data, status) {

							});
						},
						"paging" : true,
						"ordering" : false,
						"info" : false,
						"searching" : false,
						"processing" : true,
						"serverSide" : true,
						"scrollY" : '50vh',
						"scrollCollapse" : true,
						"scroller" : {
							"loadingIndicator" : true
						},
						"language" : {
							"lengthMenu" : "Display _MENU_ Records",
							"emptyTable" : "Empty",
							"processing" : "Loading...",
							"paginate" : {
								"first" : "First",
								"last" : "Last",
								"next" : "Next",
								"previous" : "Previous"
							},
						},
						"columns" : [
								{
									"name" : "#",
									"data" : function(row) {
										return row.index;
									}
								},
								{
									"name" : "key",
									"data" : function(row) {
										return row.id;
									}
								},
								{
									"name" : "type",
									"data" : function(row) {
										return row.type;
									}
								},
								{
									"name" : "values",
									"data" : function(row) {
										var id = 'value_' + row.id + '_'
												+ row.index;
										var html = '<div>';
										html += '<a data-toggle="collapse" href="#'
												+ id
												+ '" role="button" aria-expanded="false" aria-controls="'
												+ id
												+ '" data-toggle="tooltip" title="View">View</a>';
										html += '	<div class="collapse multi-collapse" id="'
												+ id + '">';
										html += '  <div class="card card-body" style="overflow-y: auto; height:180px; width: 400px" >';
										html += '<pre><code>'
												+ FormatJSON(row.values)
												+ '</code></pre>';
										;
										html += ' </div>';
										html += '</div>';
										html += '</div>';

										return html;
									}
								},
								{
									"name" : "txid",
									"data" : function(row) {
										var html = '<form id="' + row.id + "-"
												+ row.index + '" action="'
												+ baseURL
												+ '/explorer/history?key='
												+ row.id + '" method="post">';
										html += '<a href="javascript:;" onclick="document.getElementById(\''
												+ row.id
												+ "-"
												+ row.index
												+ '\').submit();" data-toggle="tooltip" title="Show History">turn on</a>';
										html += '<input type="hidden" name="type" value="'
												+ row.type + '"/>';
										html += '</form>';
										return html;
									}
								},
								{
									"name" : "operations",
									"data" : function(row) {
										var html = '';
										html += '<div class="btn-group" role="group" aria-label="操作">';
										html += '  <button type="button" class="btn btn-success btn-sm" onclick="toEdit(\''
												+ row.id + '\')">edit</button>';
										html += '  <button type="button" class="btn btn-danger btn-sm" onclick="toDelete(\''
												+ row.id + '\')">delete</button>';
										html += '</div>';
										return html;
									}
								} ]
					});

	$('#add').click(function() {

		toastInfo('Desperately creating...');

		let date = new Date();
		let value = {
			id : date.getTime(),
			type : $('#type').val(),
			values : {
				timestamp : date.getTime(),
				time : date,
				type : $('#type').val(),
				author : 'Ecsoya'
			}
		}
		let custom = $('#value').val();
		if (custom != '') {
			value.values.value = custom;
		}

		$.ajax({
			url : baseURL + "/add",
			data : value
		}).then(function success(res) {
			toastSuccess("Added successfully")
			refreshDataTable(true);
		}, function fail(data, status) {
			toastError('Add failed');
		});
	})

	$('#refresh').click(function() {
		refreshDataTable();
	});

	function refreshDataTable(force) {
		if (force != undefined && force === true) {
			bookmarkMap = new Map();
		}
		if (dataTable != undefined && dataTable != null) {
			dataTable.ajax.reload();
		}
	}

})

function toEdit(id) {
	$('#editId').val(id);
	$('#editModal').modal('show');
}

function performEdit() {

	let id = $('#editId').val();
	let type = $('#type').val();
	let values = JSON.parse($('#editValues').val());
	$.ajax({
		url : baseURL + "/update",
		data : {
			id : id,
			type : type,
			values : values,
		}
	}).then(function success(res) {
		toastSuccess('Successfully modified');
		refreshDataTable()
	}, function fail(data, status) {
		toastError('Fail to edit');
	});

	$('#editModal').modal('hide');

	toastInfo('Desperately modifying...');
}

function toDelete(id) {
	toastInfo('Desperately deleting...');
	$.ajax({
		url : baseURL + "/remove",
		data : {
			id : id,
			type : $('#type').val()
		}
	}).then(function success(res) {
		toastSuccess('Successfully deleted');

		setTimeout(function() {
			// refreshDataTable(true);
			window.location = baseURL;
		}, 2000);
	}, function fail(data, status) {
		toastError('Failed to delete');
	});
}

function toastSuccess(msg) {
	$.toast().reset('all');
	$.toast({
		text : msg,
		showHideTransition : 'slide',
		icon : 'success',
		loaderBg : '#f96868',
		position : 'top-center',
		hideAfter : 2000,
		showClose : false
	});
}

function toastError(msg) {
	$.toast().reset('all');
	$.toast({
		text : msg,
		stack : 1,
		showHideTransition : 'slide',
		icon : 'error',
		loaderBg : '#f2a654',
		position : 'top-center',
		hideAfter : 2000,
		showClose : false
	})
}

function toastInfo(msg) {
	$.toast().reset('all');
	$.toast({
		text : msg,
		stack : 1,
		showHideTransition : 'slide',
		icon : 'info',
		loaderBg : '#46c35f',
		position : 'top-center',
		hideAfter : false,
		showClose : false
	})
}