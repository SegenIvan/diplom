<#include "security.ftlh">
<#import "login.ftlh" as l>

<div class="card-columns">
    <#list orders as order>
        <#if order.user ??>
            <#if isAdmin>
                <div class="card my-3">
                    <div class="m-2">
                        <span>${order.text}</span><br/>
                        <span>${order.tag}</span><br/>
                        <span>${order.price}</span><br/>
                    </div>
                    <div class="card-footer text-muted">
                        <#if isAdmin>
                            <#if order.user ??>
                                <a href="/user-orders/${order.user.id}">${order.user.username}</a>
                            </#if>
                                <a class="btn btn-primary" href="/del-order/${order.id}">delete </a>
                        </#if>
                    </div>
                    </div>
                    </#if>
        <#else>
                    <div class="card my-3">
                    <div class="m-2">
                        <span>${order.text}</span><br/>
                        <span>${order.tag}</span><br/>
                        <span>${order.price}</span><br/>
                    </div>
                <div class="card-footer text-muted">
                    <#if isAdmin>
                        <#if order.user ??>
                            <a href="/user-orders/${order.user.id}">${order.user.username}</a>
                        </#if>
                            <a class="btn btn-primary" href="/del-order/${order.id}">delete </a>
                    </#if>
                        <#if order.user ??>
                            <a></a>
                            <#else>
                            <a class="btn btn-primary" href="/user-orders-add/${order.id}">Add</a>
                        </#if>
                </div>
            </div>
        </#if>
    </#list>
</div>