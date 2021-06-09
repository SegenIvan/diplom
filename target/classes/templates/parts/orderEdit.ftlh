<a class="btn btn-primary" data-toggle="collapse" href="#collapseExample" role="button" aria-expanded="false" aria-controls="collapseExample">
    editor
</a>
<div class="collapse <#if order??>show</#if>" id="collapseExample">
    <div class="form-group mt-3">
        <form method="post" enctype="multipart/form-data">
            <div class="form-group">
                            <input type="text" class="form-control ${(textError??)?string('is-invalid', '')}"
                                   value="<#if order??>${order.text}</#if>" name="text" placeholder="Введите сообщение" />
                            <#if textError??>
                                <div class="invalid-feedback">
                                    ${textError}
                                </div>
                            </#if>
                        </div>

            <div class="form-group">
                            <input type="text" class="form-control"
                                   value="<#if order??>${order.tag}</#if>" name="tag" placeholder="Тэг" />
                            <#if tagError??>
                                <div class="invalid-feedback">
                                    ${tagError}
                                </div>
                            </#if>
                        </div>
            <div class="form-group">
                            <input type="text" class="form-control"
                                    value="<#if order??>${order.price}</#if>" name="price" placeholder="Цена">
                            <#if priceError??>
                                  <div class="invalid-feedback">
                                     ${priceError}
                                  </div>
                            </#if>
            </div>
            <input type="hidden" name="_csrf" value="${_csrf.token}" />
            <input type="hidden" name="id" value="<#if order??>${order.id}<#else>-1</#if>" />
            <div class="form-group">
                <button type="submit" class="btn btn-primary">Save</button>
            </div>
        </form>
    </div>
</div>