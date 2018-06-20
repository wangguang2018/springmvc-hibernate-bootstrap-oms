<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="modal fade" id="addProductStock" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content encode-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" >给机器添加库存数</h4>
                <input type="hidden" name="optionId">
            </div>
            <form>
                <div class="modal-body">
                    <label class="col-md-3 control-label">添加库存数:</label>
                    <div class="col-md-9">
                        <input type="number" min="1" name="stockNum" id="stockNum" placeholder="请输入添加库存数" class="form-control param " >
                    </div>
                </div>

                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                    <button type="button" class="btn btn-primary" id="stock-submit">确定</button>
                </div>
            </form>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>

<style>
  .modal-footer{
      margin-top: 50px;
  }


</style>