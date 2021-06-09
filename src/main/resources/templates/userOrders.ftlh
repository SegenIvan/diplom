<#import "parts/common.ftlh" as c>
<#import "login.ftlh" as l>

<@c.page>
<div class="card-columns">
    <#list orders as order>
        <div class="card my-3">
            <div class="m-2">

                <span>${order.text}</span><br/>
                <span>${order.tag}</span><br/>
                <span>${order.price}</span><br/>

            </div>

                <div class="card-footer text-muted">
                  <a class="btn btn-primary" href="/del-user-order/${order.id}">delete</a>
                </div>
        </div>
    </#list>
</div>
<#list orders as order>
<#if !order?has_next>
<a class="btn btn-primary" href="/confirm-order/">Confirm</a>
<#else>

</#if>
</#list>
</@c.page>