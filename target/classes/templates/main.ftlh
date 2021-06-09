<#import "parts/common.ftlh" as c>
<#include "parts/security.ftlh">
<#import "parts/login.ftlh" as l>

<@c.page>
<div class="form-row">
    <div class="form-group col-md-6">
        <form method="get" action="/main" class="form-inline">
            <input type="text" name="filter" class="form-control" value="${filter?ifExists}" placeholder="Search by tag">
            <button type="submit" class="btn btn-primary ml-2">Search</button>
        </form>
    </div>
</div>

<#if isAdmin><#include "parts/orderEdit.ftlh" /></#if>

<#include "parts/orderList.ftlh" />

</@c.page>